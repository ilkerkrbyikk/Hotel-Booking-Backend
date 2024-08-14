package com.IlkerKarabiyik.HotelService.dto.responses;

import com.IlkerKarabiyik.HotelService.dto.requests.ReservationDto;
import lombok.Data;

import java.util.List;

@Data
public class ReservationResponseDto {

    private int totalPages;

    private int pageNumber;

    private List<ReservationDto> reservationDtoList;


}
