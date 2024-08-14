package com.IlkerKarabiyik.HotelService.dto.responses;

import com.IlkerKarabiyik.HotelService.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private Long id;

    private UserRole userRole;

}
