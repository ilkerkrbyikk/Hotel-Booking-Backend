package com.IlkerKarabiyik.HotelService.services.auth;

import com.IlkerKarabiyik.HotelService.dto.UserDto;
import com.IlkerKarabiyik.HotelService.dto.requests.SignUpRequest;
import com.IlkerKarabiyik.HotelService.entity.User;
import com.IlkerKarabiyik.HotelService.enums.UserRole;
import com.IlkerKarabiyik.HotelService.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setPassword("12345");
            user.setUserRole(UserRole.ADMIN);

            user.setUsername(new BCryptPasswordEncoder().encode("admin"));

            userRepository.save(user);
            System.out.println("Admin hesabı başarıyla kaydedildi.");

        }
        else {
            System.out.println("Admin hesabı bulunuyor.");
        }

    }

    public UserDto createUser(SignUpRequest signUpRequest){

        if(userRepository.findFirstByEmail(signUpRequest.getEmail()).isPresent()){

            throw new EntityExistsException("Bu mail adresiyle kullanıcı bulunmaktadır." + signUpRequest.getEmail());
        }


        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setUsername(signUpRequest.getName());
        user.setPassword (new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }

}
