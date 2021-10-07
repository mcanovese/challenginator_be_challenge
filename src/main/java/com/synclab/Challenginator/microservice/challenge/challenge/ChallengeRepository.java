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

    //seleziono tutte le sfide sia passive che attive dell'utente
    @Query("SELECT u FROM Challenge u WHERE u.challenged = ?1 OR u.challenger = ?1")
    List<Challenge> getAllChallenge (Long id);

    List<Challenge> findChallengesByChallenged(Long data); //sfidato
    List<Challenge> findChallengesByChallenger(Long data); //sfidante


}
