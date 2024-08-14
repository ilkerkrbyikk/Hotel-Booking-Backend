package com.IlkerKarabiyik.HotelService.services.customer.booking;

import com.IlkerKarabiyik.HotelService.dto.requests.ReservationDto;
import com.IlkerKarabiyik.HotelService.dto.responses.ReservationResponseDto;

public interface BookingService {

    boolean postReservation(ReservationDto reservationDto);
    ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber);
}
