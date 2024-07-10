package com.kukuxer.registration.domain;

import com.kukuxer.registration.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "recovery_password",schema = "chessonline")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecoveryPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "text")
    private String token;

    @CreationTimestamp
    @Column(name = "start_time")
    private LocalDateTime startTime;
}
