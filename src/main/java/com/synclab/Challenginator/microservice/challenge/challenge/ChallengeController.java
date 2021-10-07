package com.synclab.Challenginator.microservice.challenge.challenge;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.core.util.Json;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@RestController
public class ChallengeController {


    private ChallengeService challengeService;

    @Autowired
    private RestTemplate restTemplate;


    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/challenge")
    public List<Challenge> getChallenge(@RequestHeader(name="Authorization") String jwt) throws Exception {

        challengeService.authCheck(jwt);
        return challengeService.getALlChallenge(112L);
    }

    @GetMapping("/challenge/{id}")
    public Optional<Challenge> getChallengeById(@PathVariable Long id) {
        return challengeService.getChallengeById(id);
    }

    @PutMapping("/challenge")
    public Challenge updateChallenge(@RequestBody Challenge challenge){
        return challengeService.updateChallenge(challenge);
    }

    @DeleteMapping("/challenge/{id}")
    public String deleteChallenge(@PathVariable Long id){
         challengeService.deleteChallenge(id);
         return "{\"operation\":ok}";
    }


   // TEST PER COMUNICAZIONE CON REST TEMPLATE

    @GetMapping("/challenge/test")
    public Boolean test(@RequestHeader(name= "Authorization") String jwt) throws Exception {
        challengeService.authCheck(jwt);
        return true;
    }





}

