package com.labadmin.domain.reservation;

import com.labadmin.domain.common.BaseTimeEntity;
import com.labadmin.domain.server.LabServer;
import com.labadmin.domain.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation",
        indexes = {
                @Index(name = "idx_reservation_server_start_end",
                        columnList = "server_id, start_at, end_at")
        })
public class Reservation extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "server_id")
    private LabServer server;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Column(nullable = false, length = 200)
    private String purpose;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservationStatus status; // REQUESTED/APPROVED/REJECTED/CANCELLED

    public void approve() {
        this.status = ReservationStatus.APPROVED;
    }

    public void reject() {
        this.status = ReservationStatus.REJECTED;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }

}
