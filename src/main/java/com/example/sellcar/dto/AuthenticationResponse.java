package com.example.sellcar.dto;

import com.example.sellcar.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private Long id;
    private UserRole role;
    private String jwt;
}
