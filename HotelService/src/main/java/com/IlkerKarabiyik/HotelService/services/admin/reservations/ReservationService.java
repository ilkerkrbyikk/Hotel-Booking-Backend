package com.IlkerKarabiyik.HotelService.services.admin.reservations;

import com.IlkerKarabiyik.HotelService.dto.responses.ReservationResponseDto;

public interface ReservationService {

    ReservationResponseDto getAllReservations(int pageNumber);

    boolean changeReservationStatus(Long id, String status);
}
