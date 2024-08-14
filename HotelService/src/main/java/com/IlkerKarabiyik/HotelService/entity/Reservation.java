package com.IlkerKarabiyik.HotelService.entity;

import com.IlkerKarabiyik.HotelService.dto.requests.ReservationDto;
import com.IlkerKarabiyik.HotelService.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long price;

    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public ReservationDto getReservationDto(){
        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setId(id);
        reservationDto.setPrice(price);
        reservationDto.setReservationStatus(reservationStatus);
        reservationDto.setCheckInDate(checkInDate);
        reservationDto.setCheckOutDate(checkOutDate);

        reservationDto.setUserId(user.getId());
        reservationDto.setUserName(user.getUsername());

        reservationDto.setRoomId(room.getId());
        reservationDto.setRoomType(room.getType());
        reservationDto.setRoomName(room.getName());

        return reservationDto;

    }

}
