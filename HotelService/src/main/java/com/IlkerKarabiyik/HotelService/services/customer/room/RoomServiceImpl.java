package com.IlkerKarabiyik.HotelService.services.customer.room;

import com.IlkerKarabiyik.HotelService.dto.responses.RoomResponseDto;
import com.IlkerKarabiyik.HotelService.entity.Room;
import com.IlkerKarabiyik.HotelService.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomResponseDto getAvailableRooms(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,6); // * Page size bir sayfada kaç oda+
        Page<Room> roomPage =  roomRepository.findByAvailable(true,pageable); // * gösterileceğini gösteriyor.

        RoomResponseDto roomResponseDto = new RoomResponseDto();
        roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomResponseDto.setTotalPages(roomPage.getTotalPages());
        roomResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return roomResponseDto;
    }
}
