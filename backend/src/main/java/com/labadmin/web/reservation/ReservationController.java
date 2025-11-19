package com.labadmin.web.reservation;

import com.labadmin.service.ReservationService;
import com.labadmin.web.dto.reservation.ReservationCreateRequest;
import com.labadmin.web.dto.reservation.ReservationResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> create(@Valid @RequestBody ReservationCreateRequest request) {
        return ResponseEntity.ok(reservationService.createReservation(request));
    }

    @GetMapping("/reservations/my")
    public ResponseEntity<List<ReservationResponse>> myReservations() {
        return ResponseEntity.ok(reservationService.getMyReservations());
    }

    @PatchMapping("/reservations/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}
