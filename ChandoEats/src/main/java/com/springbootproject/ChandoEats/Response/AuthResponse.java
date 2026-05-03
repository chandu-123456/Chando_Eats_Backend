package com.springbootproject.ChandoEats.Response;

import com.springbootproject.ChandoEats.Domain.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    public String email;
    public String jwt;
    private USER_ROLE role;
    public String message;
}
