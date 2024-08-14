package com.IlkerKarabiyik.HotelService.dto;

import com.IlkerKarabiyik.HotelService.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}
