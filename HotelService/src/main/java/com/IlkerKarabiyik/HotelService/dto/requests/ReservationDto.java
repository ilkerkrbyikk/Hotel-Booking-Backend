package com.IlkerKarabiyik.HotelService.dto.requests;

import com.IlkerKarabiyik.HotelService.enums.ReservationStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long price;

    private ReservationStatus reservationStatus;

    private Long roomId;

    private String roomType;

    private String roomName;

    private Long userId;

    private String userName;

}
