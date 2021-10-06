package com.synclab.Challenginator.microservice.challenge.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    /*
    @GetMapping("/challenge")
    public String getChallenge() {
        //return challengeService.getUserChallenge(id);

        return "ciao";

    }


     */









}

