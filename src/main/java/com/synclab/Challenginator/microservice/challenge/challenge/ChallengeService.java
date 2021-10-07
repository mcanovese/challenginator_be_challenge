package com.synclab.Challenginator.microservice.challenge.challenge;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {


    private  ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    //ricerco sfide in cui ID è sfidante
    public List<Challenge> getUserChallenge(Long id){

        return challengeRepository.findChallengesByChallenger(id);
    }

    //ricerco sfide in cuiId è sfidato
    public List<Challenge> getUserChallengeOut(Long id){
        return challengeRepository.findChallengesByChallenged(id);
    }

    public List<Challenge> getALlChallenge(Long id){
        return challengeRepository.getAllChallenge(id);
    }

    public Optional<Challenge> getChallengeById(Long id){
        return challengeRepository.findById(id);
    }

    public Challenge updateChallenge(Challenge challenge){
        return challengeRepository.save(challenge);
    }

    public String insertNewChallenge(Challenge challenge){
        challengeRepository.save(challenge);

        return "success - challenge inserita";
    }

    public void deleteChallenge(Long id){
        challengeRepository.deleteById(id);
    }







}
