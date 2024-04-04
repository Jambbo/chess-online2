package com.kukuxer.registration.domain.request;

import com.kukuxer.registration.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="search_request")
public class SearchRequest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    private User sender;
    private int minRating;
    private int maxRating;
    private boolean waiting;
    private LocalDateTime createdAt;
}