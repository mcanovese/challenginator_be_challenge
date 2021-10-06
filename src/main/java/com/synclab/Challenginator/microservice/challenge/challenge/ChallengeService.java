package com.synclab.Challenginator.microservice.challenge.challenge;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ChallengeService {


    private  ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public List<Challenge> getUserChallenge(Long id){
        return challengeRepository.findByIdChallenged(id);
    }


    public String insertNewChallenge(Challenge challenge){
        challengeRepository.save(challenge);

        return "success - challenge inserita";
    }







}
