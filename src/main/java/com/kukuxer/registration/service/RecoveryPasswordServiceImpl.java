package com.kukuxer.registration.service;

import com.kukuxer.registration.domain.user.User;
import com.kukuxer.registration.repository.RecoveryPasswordRepository;
import com.kukuxer.registration.repository.UserRepository;
import com.kukuxer.registration.service.interfaces.RecoveryPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class RecoveryPasswordServiceImpl implements RecoveryPasswordService {

    private final RecoveryPasswordRepository recoveryPasswordRepository;
    private final EmailServiceImpl emailService;
    private final UserRepository userRepository;

    private static String[] words = new String[]{"max","stas","evreniy","aboba","vov4ik","zavod","pivo","angry","chess"};
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 30;

    @Override
    public void sendEmailToRecoverThePassword(String email){

        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new RuntimeException("user not found with email: "+email)
        );

        emailService.sendMailTo(email,"http://localhost:8080/passwordRecovery/forgotPassword/"+generateRandomString());
    }

    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
            if(i%5==0){
                int randomWordIndex = random.nextInt(words.length-1);
                sb.append(words[randomWordIndex]);
            }
        }
        return sb.toString();
    }
}
