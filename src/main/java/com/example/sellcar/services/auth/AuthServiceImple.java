package com.example.sellcar.services.auth;

import com.example.sellcar.dto.SignUpRequest;
import com.example.sellcar.dto.UserDTO;
import com.example.sellcar.entity.User;
import com.example.sellcar.enums.UserRole;
import com.example.sellcar.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImple implements AuthService{
    @Autowired
    private UserRepository userRepository;
@PostConstruct
    public void createAnAdminAccount() {
        Optional<User> optionalAdmin = userRepository.findByUserRole(UserRole.ADMIN);
        if (optionalAdmin.isEmpty()) {
            User admin = new User();
                            admin.setName("Admin");
            admin.setEmail("admin@test.com");
            admin.setUserRole(UserRole.ADMIN);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));



            userRepository.save(admin);
            System.out.println("Admin account created successfully");
        } else {
            System.out.println("Admin account already exist!");


        }
    }

    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @Override
    public UserDTO signUp(SignUpRequest request) {
   User user=new User();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
           user.setUserRole(UserRole.CUSTOMER);


   return userRepository.save(user).getDTO();
        //return null;
    }
}
