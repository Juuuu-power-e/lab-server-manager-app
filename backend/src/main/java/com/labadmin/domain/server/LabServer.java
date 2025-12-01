package com.labadmin.domain.server;
import com.labadmin.domain.common.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
public class LabServer extends BaseTimeEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ipAddress;
    private String description;
    private String gpuInfo;
    private String location;
    private boolean active;
}
