package com.kukuxer.registration.controller;


import com.kukuxer.registration.domain.request.Request;
import com.kukuxer.registration.domain.user.User;
import com.kukuxer.registration.service.MatchService;
import com.kukuxer.registration.service.RequestService;
import com.kukuxer.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final UserService userService;
    private final RequestService requestService;
    private final MatchService matchService;


    @PostMapping("/send/{receiverId}")
    public ResponseEntity<String> sendRequest(@PathVariable("receiverId") long receiverId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            System.out.println(username);
            User userSender = userService.getByUsername(username);
            if (userSender == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user cookie provided.");
            }
            requestService.createRequest(userSender.getId(), receiverId);
            return ResponseEntity.ok("Request sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending request.");
        }
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptRequest(@PathVariable("requestId") long requestId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userReceiver = userService.getByUsername(authentication.getName());
        requestService.acceptRequest(requestId,userReceiver.getId());
        return ResponseEntity.ok("Request accept successfully.");
    }
    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable("requestId") long requestId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName()+" " + authentication.getAuthorities()+" " + authentication.getPrincipal());
        User userReceiver = userService.getByUsername(authentication.getName());
        requestService.rejectRequest(requestId,userReceiver.getId());
        return ResponseEntity.ok("Request rejected successfully.");
    }
}