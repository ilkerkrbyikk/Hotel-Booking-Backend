package com.IlkerKarabiyik.HotelService.controller.admin;

import com.IlkerKarabiyik.HotelService.enums.ReservationStatus;
import com.IlkerKarabiyik.HotelService.services.admin.reservations.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservations/{pageNumber}")
    public ResponseEntity<?> getAllReservations(@PathVariable int pageNumber){
         try{
             return ResponseEntity.ok(reservationService.getAllReservations(pageNumber));
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Üzgünüm bir şeyler yanlış bitti.");
         }
    }

    @GetMapping("/reservation/{id}/{status}")
    public ResponseEntity<?> changeReservationStatus(@PathVariable Long id, String status){
        boolean success = reservationService.changeReservationStatus(id, status);

        if (success){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Üzgünüm bir şeyler ters gitti.");
        }
    }


    @GetMapping("/reservation/{userId}/{pageNumber}")
    public ResponseEntity<?> getAllBookingByUserId(@PathVariable Long userId, @PathVariable int pageNumber){

        try{
            return ResponseEntity.ok(getAllBookingByUserId(userId, pageNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Üzgünüm bir şeyler ters gitti.");
        }
    }
}
