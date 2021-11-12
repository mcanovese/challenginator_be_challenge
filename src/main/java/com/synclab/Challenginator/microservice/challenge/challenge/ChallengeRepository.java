package com.synclab.Challenginator.microservice.challenge.challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
Unico repository di questo microservizio, estende JPA Repository
espone query predefinite ed alcune custom
 */

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long>{

    @Override
    Optional<Challenge> findById(Long aLong);

    //seleziono tutte le sfide sia passive che attive dell'utente
    @Query("SELECT u FROM Challenge u WHERE u.challenged = ?1 OR u.challenger = ?1")
    List<Challenge> getAllChallenge (Long id);


    //selezione tutte le sfide in status X (richiesto) in cui sono coinvolto come sfidante o sfidato
    @Query("SELECT s FROM Challenge s WHERE s.status = ?1 AND s.challenger = ?2 OR s.challenged = ?2")
    List<Challenge> getUserChallengeByStatus(ChallengeStatus status, Long id);



    List<Challenge> findChallengesByChallenged(Long data); //sfidato
    List<Challenge> findChallengesByChallenger(Long data); //sfidante
    List<Challenge> findChallengesByEvaluator(Long data); // sfide che devo valutare
    Challenge getChallengeById(Long id);

}
