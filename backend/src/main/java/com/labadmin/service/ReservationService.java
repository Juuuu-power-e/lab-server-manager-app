package com.labadmin.service;

import com.labadmin.domain.reservation.Reservation;
import com.labadmin.domain.reservation.ReservationStatus;
import com.labadmin.domain.server.LabServer;
import com.labadmin.domain.user.User;
import com.labadmin.repository.LabServerRepository;
import com.labadmin.repository.ReservationRepository;
import com.labadmin.repository.UserRepository;
import com.labadmin.web.dto.reservation.ReservationCreateRequest;
import com.labadmin.web.dto.reservation.ReservationResponse;
import com.labadmin.web.error.BusinessException;
import com.labadmin.web.error.ErrorCode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final LabServerRepository labServerRepository;
    private final UserRepository userRepository;
    private final CurrentUserProvider currentUserProvider; // SecurityContext에서 꺼낸다고 가정

    @Transactional
    public ReservationResponse createReservation(ReservationCreateRequest request) {
        if (!request.getStartAt().isBefore(request.getEndAt())) {
            throw new BusinessException(ErrorCode.RESERVATION_TIME_INVALID);
        }

        LabServer server = labServerRepository.findById(request.getServerId())
                .orElseThrow(() -> new BusinessException(ErrorCode.SERVER_NOT_FOUND));

        Long currentUserId = currentUserProvider.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 겹치는 예약 체크
        List<Reservation> overlaps = reservationRepository.findOverlappingReservations(
                server.getId(),
                request.getStartAt(),
                request.getEndAt(),
                List.of(ReservationStatus.REQUESTED, ReservationStatus.APPROVED)
        );
        if (!overlaps.isEmpty()) {
            throw new BusinessException(ErrorCode.RESERVATION_OVERLAP);
        }

        Reservation reservation = Reservation.builder()
                .server(server)
                .user(user)
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .purpose(request.getPurpose())
                .status(ReservationStatus.REQUESTED)
                .build();

        Reservation saved = reservationRepository.save(reservation);
        return ReservationResponse.from(saved);
    }

    public List<ReservationResponse> getMyReservations() {
        Long currentUserId = currentUserProvider.getCurrentUserId();
        return reservationRepository.findByUserIdOrderByStartAtDesc(currentUserId)
                .stream()
                .map(ReservationResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Long currentUserId = currentUserProvider.getCurrentUserId();

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESERVATION_NOT_FOUND));

        // 본인 예약이거나 ADMIN만 취소
        if (!reservation.getUser().getId().equals(currentUserId)
                && !currentUserProvider.isAdmin()) {
            throw new BusinessException(ErrorCode.RESERVATION_FORBIDDEN);
        }

        reservation.cancel();
    }
}
