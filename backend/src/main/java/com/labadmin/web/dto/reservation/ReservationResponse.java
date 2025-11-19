package com.labadmin.web.dto.reservation;

import com.labadmin.domain.reservation.Reservation;
import com.labadmin.domain.reservation.ReservationStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationResponse {

    private Long id;
    private Long serverId;
    private String serverName;
    private Long userId;
    private String username;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String purpose;
    private ReservationStatus status;

    public static ReservationResponse from(Reservation r) {
        return ReservationResponse.builder()
                .id(r.getId())
                .serverId(r.getServer().getId())
                .serverName(r.getServer().getName())
                .userId(r.getUser().getId())
                .username(r.getUser().getUsername())
                .startAt(r.getStartAt())
                .endAt(r.getEndAt())
                .purpose(r.getPurpose())
                .status(r.getStatus())
                .build();
    }
}
