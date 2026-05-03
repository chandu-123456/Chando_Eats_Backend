package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.model.PasswordResetToken;

public interface PasswordResetTokenService {

    public PasswordResetToken findByToken(String token);

    public void delete(PasswordResetToken resetToken);
}
