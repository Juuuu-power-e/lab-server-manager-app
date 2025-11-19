package com.labadmin.repository;

import com.labadmin.domain.reservation.Reservation;
import com.labadmin.domain.reservation.ReservationStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
        select r from Reservation r
        where r.server.id = :serverId
          and r.status in :statuses
          and r.startAt < :endAt
          and r.endAt > :startAt
        """)
    List<Reservation> findOverlappingReservations(
            Long serverId,
            LocalDateTime startAt,
            LocalDateTime endAt,
            List<ReservationStatus> statuses
    );

    List<Reservation> findByUserIdOrderByStartAtDesc(Long userId);
}
