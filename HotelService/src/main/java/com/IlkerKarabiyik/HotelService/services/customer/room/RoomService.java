package com.IlkerKarabiyik.HotelService.services.customer.room;

import com.IlkerKarabiyik.HotelService.dto.responses.RoomResponseDto;

public interface RoomService {

    RoomResponseDto getAvailableRooms(int pageNumber);
}
