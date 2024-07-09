package com.kukuxer.registration.service;

import com.kukuxer.registration.repository.RecoveryPasswordRepository;
import com.kukuxer.registration.service.interfaces.RecoveryPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecoveryPasswordPasswordServiceImpl implements RecoveryPasswordService {

    private final RecoveryPasswordRepository recoveryPasswordRepository;
    private final EmailServiceImpl emailService;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 30;

    @Override
    public void sendEmailToRecoverThePassword(String email){
        emailService.sendMailTo(email,"");
    }

}
