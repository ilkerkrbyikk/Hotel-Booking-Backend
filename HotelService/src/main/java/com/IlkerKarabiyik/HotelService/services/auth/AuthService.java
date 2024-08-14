package com.IlkerKarabiyik.HotelService.services.auth;

import com.IlkerKarabiyik.HotelService.dto.UserDto;
import com.IlkerKarabiyik.HotelService.dto.requests.SignUpRequest;

public interface AuthService {

    UserDto createUser(SignUpRequest signUpRequest);
}
