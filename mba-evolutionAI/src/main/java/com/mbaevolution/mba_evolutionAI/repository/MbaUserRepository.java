package com.mbaevolution.mba_evolutionAI.repository;

import com.mbaevolution.mba_evolutionAI.entity.MbaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MbaUserRepository extends JpaRepository<MbaUser, Long> {
    Optional<MbaUser> findByMbaUsername(String mbaUsername);
}