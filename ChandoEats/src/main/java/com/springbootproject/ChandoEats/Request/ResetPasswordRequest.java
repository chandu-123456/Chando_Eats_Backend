package com.springbootproject.ChandoEats.Request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String password;
    private String token;
}
