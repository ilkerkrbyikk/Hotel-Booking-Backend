package com.IlkerKarabiyik.HotelService.controller.auth;

import com.IlkerKarabiyik.HotelService.dto.UserDto;
import com.IlkerKarabiyik.HotelService.dto.requests.AuthenticationRequest;
import com.IlkerKarabiyik.HotelService.dto.requests.SignUpRequest;
import com.IlkerKarabiyik.HotelService.dto.responses.AuthenticationResponse;
import com.IlkerKarabiyik.HotelService.entity.User;
import com.IlkerKarabiyik.HotelService.repository.UserRepository;
import com.IlkerKarabiyik.HotelService.services.auth.AuthService;
import com.IlkerKarabiyik.HotelService.services.jwt.UserService;
import com.IlkerKarabiyik.HotelService.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpRequest(@RequestBody SignUpRequest signUpRequest){
        try{
            UserDto createdUser = authService.createUser(signUpRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }
        catch (EntityExistsException exception){
            return new ResponseEntity<>("Kullanıcı bulunuyor.", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("Üye kaydı tamamlanamadı. Tekrar deneyiniz.", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e){
            throw new BadCredentialsException("Hatalı kullanıcı adı veya şifre.");

        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }
        return authenticationResponse;


    }
}
