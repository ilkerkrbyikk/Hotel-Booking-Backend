package com.IlkerKarabiyik.HotelService.entity;

import com.IlkerKarabiyik.HotelService.dto.RoomDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private Long price;

    private boolean available;

    public RoomDto getRoomDto(){
        RoomDto roomDto = new RoomDto();

        roomDto.setId(id);
        roomDto.setName(name);
        roomDto.setPrice(price);
        roomDto.setType(type);
        roomDto.setAvailable(available);

        return roomDto;
    }

}
