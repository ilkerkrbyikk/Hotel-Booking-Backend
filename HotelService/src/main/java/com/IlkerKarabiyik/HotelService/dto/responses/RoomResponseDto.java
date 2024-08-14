package com.IlkerKarabiyik.HotelService.dto.responses;

import com.IlkerKarabiyik.HotelService.dto.RoomDto;
import lombok.Data;

import java.util.List;

@Data
public class RoomResponseDto {
    private List<RoomDto> roomDtoList;

    private int totalPages;

    private int pageNumber;
}
