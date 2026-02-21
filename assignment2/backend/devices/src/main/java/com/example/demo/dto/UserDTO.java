package com.example.demo.dto;
import lombok.Data;
import com.example.demo.entities.UserRole;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private UserRole role;
}
