package com.labadmin.web.dto.server;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LabServerCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String ipAddress;

    private String description;

    private String gpuInfo;

    private String location;

    @NotNull
    private Boolean active;
}
