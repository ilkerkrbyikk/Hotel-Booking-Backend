package com.IlkerKarabiyik.HotelService.dto.requests;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}
