package com.kukuxer.registration.repository;

import com.kukuxer.registration.domain.RecoveryPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecoveryPasswordRepository extends JpaRepository<RecoveryPassword,Long> {
}
