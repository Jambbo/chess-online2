package com.kukuxer.registration.controller;

import com.kukuxer.registration.domain.RecoveryPassword;
import com.kukuxer.registration.service.EmailServiceImpl;
import com.kukuxer.registration.service.interfaces.RecoveryPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passwordRecovery")
@RequiredArgsConstructor
public class RecoveryPasswordController {

    private final RecoveryPasswordService recoveryPasswordService;

    @PostMapping("/forgotPassword/{email}")
    public void sendEmailToRecoverThePassword(@PathVariable String email){
        recoveryPasswordService.sendEmailToRecoverThePassword(email);
    }
}
