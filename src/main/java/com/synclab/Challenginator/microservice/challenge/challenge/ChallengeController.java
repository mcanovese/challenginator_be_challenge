package com.synclab.Challenginator.microservice.challenge.challenge;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.synclab.Challenginator.microservice.challenge.insertChallenge.InsertController;
import com.synclab.Challenginator.microservice.challenge.insertChallenge.InsertRequest;
import com.synclab.Challenginator.microservice.challenge.insertChallenge.InsertService;
import io.swagger.v3.core.util.Json;
import netscape.javascript.JSObject;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.naming.ldap.Control;
import java.util.List;
import java.util.Optional;

@RestController
public class ChallengeController {

    private InsertService insertService;
    private ChallengeService challengeService;

    @Autowired
    private RestTemplate restTemplate;


    public ChallengeController(ChallengeService challengeService, InsertService insertService) {
        this.challengeService = challengeService;
        this.insertService = insertService;
    }

    @GetMapping("/challenge")
    public List<Challenge> getChallenge(@RequestHeader(name="Authorization") String jwt) throws Exception {
        challengeService.authCheck(jwt);
        return challengeService.getALlChallenge(112L);
    }

    @GetMapping("/challenge/{id}")
    public Challenge getChallengeById(@PathVariable Long id, @RequestHeader(name="Authorization") String jwt)
            throws Exception {
        challengeService.authCheck(jwt);
        return challengeService.getChallengeById(id);
    }

    @PutMapping("/challenge")
    public Challenge updateChallenge(@RequestBody Challenge challenge, @RequestHeader(name="Authorization") String jwt)
            throws Exception{
        challengeService.authCheck(jwt);
        return challengeService.updateChallenge(challenge);
    }

    @DeleteMapping("/challenge/{id}")
    public String deleteChallenge(@PathVariable Long id,@RequestHeader(name="Authorization") String jwt)
            throws Exception{
        challengeService.authCheck(jwt);
         challengeService.deleteChallenge(id);
         return "{\"operation\":ok}";
    }


    @PostMapping("/challenge/evaluate") //Valutazione da parte del valutatore
    public String evaluate(@RequestBody ValutatorRequest request,
                           @RequestHeader(name="Authorization") String jwt) throws Exception{
        Long idValutatore = challengeService.authCheck(jwt); //recupero id di chi valuta e verifico token
       return challengeService.evaluateChallenge(request,idValutatore);
    }

    //operazioni utente sulla challenge, accetta e/o rifiuta in base al contenuto della request
    @PostMapping("/challenge/control")
    public String control(@RequestBody ControlRequest request,
                           @RequestHeader(name="Authorization") String jwt) throws Exception{
        Long userId = challengeService.authCheck(jwt); //recupero id di chi valuta e verifico token
        return challengeService.userChallengeOperation(request,userId);
    }


   // TEST PER COMUNICAZIONE CON REST TEMPLATE
    @GetMapping("/challenge/test")
    public Long test(@RequestHeader(name= "Authorization") String jwt) throws Exception {
       return insertService.getEvaluator(3L,jwt);
    }

}