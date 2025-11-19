package com.labadmin.domain.server;

import com.labadmin.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lab_server", uniqueConstraints = {
        @UniqueConstraint(name = "uk_lab_server_name", columnNames = "name")
})
public class LabServer extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String name; // "서버 A", "서버 B" 등 논리명

    @Column(nullable = false, length = 15)
    private String ipAddress;  // 10.10.10.100 같은 내부 IP

    @Column(length = 255)
    private String description;

    @Column(length = 100)
    private String gpuInfo; // "RTX 6000 96GB"

    @Column(length = 50)
    private String location; // "공학관 5층 랩실" 등

    @Column(nullable = false)
    private boolean active; // 사용 가능 여부
}
