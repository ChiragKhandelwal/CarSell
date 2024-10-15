package com.example.sellcar.dto;

import com.example.sellcar.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private UserRole userRole;

}
