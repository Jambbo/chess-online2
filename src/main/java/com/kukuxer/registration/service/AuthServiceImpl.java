package com.kukuxer.registration.service;


import com.kukuxer.registration.domain.user.User;
import com.kukuxer.registration.security.JwtRequest;
import com.kukuxer.registration.security.JwtResponse;
import com.kukuxer.registration.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            User user = userService.getByUsername(loginRequest.getUsername());
            jwtResponse.setId(user.getId());
            jwtResponse.setUsername(user.getUsername());
            jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getUsername()));
            jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));
            return jwtResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AccessDeniedException("login error");
        }
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}