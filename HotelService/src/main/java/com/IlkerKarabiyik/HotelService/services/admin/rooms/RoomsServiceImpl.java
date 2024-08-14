package com.IlkerKarabiyik.HotelService.services.admin.rooms;

import com.IlkerKarabiyik.HotelService.dto.RoomDto;
import com.IlkerKarabiyik.HotelService.dto.responses.RoomResponseDto;
import com.IlkerKarabiyik.HotelService.entity.Room;
import com.IlkerKarabiyik.HotelService.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomService{

    private final RoomRepository roomRepository;

    public boolean postRoom(RoomDto roomDto){
        try{
            Room room = new Room();

            room.setName(roomDto.getName());
            room.setPrice(room.getPrice());
            room.setType(room.getType());
            room.setAvailable(true);

            roomRepository.save(room);
            return true;
        }

        catch (Exception e){
            return false;
        }
    }

    public RoomResponseDto getAllRooms(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,6); // * Page size bir sayfada kaç oda+
         Page<Room> roomPage =  roomRepository.findAll(pageable); // * gösterileceğini gösteriyor.

         RoomResponseDto roomResponseDto = new RoomResponseDto();
         roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
         roomResponseDto.setTotalPages(roomPage.getTotalPages());
         roomResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

         return roomResponseDto;
    }

    public RoomDto getRoomById(Long id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if(optionalRoom.isPresent()){
            return optionalRoom.get().getRoomDto();
        }
        else {
            throw new EntityNotFoundException("Oda bulunamamaktadır.");
        }
    }

    public boolean updateRoom(Long id, RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            Room existingRoom = optionalRoom.get();

            existingRoom.setName(roomDto.getName());
            existingRoom.setPrice(roomDto.getPrice());
            existingRoom.setType(roomDto.getType());

            roomRepository.save(existingRoom);
            return true;
        }
        return false;
    }

    public void deleteRoom(Long id){
        Optional<Room> optionalRoom = roomRepository.findById(id);

        if (optionalRoom.isPresent()){

            roomRepository.deleteById(id);
        }

        else {
            throw new EntityNotFoundException("Oda bulunamamaktadır.");
        }
    }

}
