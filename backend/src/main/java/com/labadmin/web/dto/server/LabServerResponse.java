package com.labadmin.web.dto.server;

import com.labadmin.domain.server.LabServer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LabServerResponse {

    private Long id;
    private String name;
    private String ipAddress;
    private String description;
    private String gpuInfo;
    private String location;
    private boolean active;

    public static LabServerResponse from(LabServer server) {
        return LabServerResponse.builder()
                .id(server.getId())
                .name(server.getName())
                .ipAddress(server.getIpAddress())
                .description(server.getDescription())
                .gpuInfo(server.getGpuInfo())
                .location(server.getLocation())
                .active(server.isActive())
                .build();
    }
}
