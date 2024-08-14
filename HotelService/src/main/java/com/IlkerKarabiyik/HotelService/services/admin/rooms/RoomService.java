package com.IlkerKarabiyik.HotelService.services.admin.rooms;

import com.IlkerKarabiyik.HotelService.dto.RoomDto;
import com.IlkerKarabiyik.HotelService.dto.responses.RoomResponseDto;

public interface RoomService {

     boolean postRoom(RoomDto roomDto);
     RoomResponseDto getAllRooms(int pageNumber);
     RoomDto getRoomById(Long id);
     boolean updateRoom(Long id, RoomDto roomDto);
     void deleteRoom(Long id);
}
