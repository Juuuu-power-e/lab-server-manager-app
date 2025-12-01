package com.labadmin.domain.reservation;
import com.labadmin.domain.common.BaseTimeEntity;
import com.labadmin.domain.server.LabServer;
import com.labadmin.domain.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation extends BaseTimeEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    private LabServer server;
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String purpose;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
