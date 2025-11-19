package com.labadmin.web.dto.reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservationCreateRequest {

    @NotNull
    private Long serverId;

    @NotNull @Future
    private LocalDateTime startAt;

    @NotNull @Future
    private LocalDateTime endAt;

    @NotBlank
    private String purpose;
}
