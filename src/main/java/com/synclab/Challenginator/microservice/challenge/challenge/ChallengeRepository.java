package com.synclab.Challenginator.microservice.challenge.challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long>{

    @Override
    Optional<Challenge> findById(Long aLong);

    List<Challenge> findByIdChallenged(Long data);
    List<Challenge> findByIdChallenger(Long data);


}
