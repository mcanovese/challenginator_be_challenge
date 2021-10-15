package com.synclab.Challenginator.microservice.challenge.challenge;


import com.synclab.Challenginator.microservice.challenge.insertChallenge.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


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
    public List<Challenge> getChallenge(@RequestHeader(name="Authorization") String jwt)   {
       Long userId =  challengeService.authCheck(jwt);
        return challengeService.getALlChallenge(userId);
    }

    @GetMapping("/challenge/{id}")
    public Challenge getChallengeById(@PathVariable Long id, @RequestHeader(name="Authorization") String jwt)
           {
        challengeService.authCheck(jwt);
        return challengeService.getChallengeById(id);
    }

    @PutMapping("/challenge")
    public HttpStatus updateChallenge(@RequestBody Challenge challenge, @RequestHeader(name="Authorization") String jwt)
             {
        challengeService.authCheck(jwt);
        Boolean result =  challengeService.updateChallenge(challenge);
        if (result) return HttpStatus.OK;
        else return HttpStatus.BAD_REQUEST;
    }


    @DeleteMapping("/challenge/{id}")
    public HttpStatus deleteChallenge(@PathVariable Long id,@RequestHeader(name="Authorization") String jwt)   {
        Long userId = challengeService.authCheck(jwt);
        return challengeService.deleteChallenge(id, userId);

    }

    @PostMapping("/challenge/evaluate") //Valutazione da parte del valutatore
    public HttpStatus evaluate(@RequestBody ValutatorRequest request,
                           @RequestHeader(name="Authorization") String jwt)  {
        Long idValutatore = challengeService.authCheck(jwt); //recupero id di chi valuta e verifico token
       return challengeService.evaluateChallenge(request,idValutatore);
    }



    //operazioni utente sulla challenge, accetta e/o rifiuta in base al contenuto della request
    @PostMapping("/challenge/control")
    public HttpStatus control(@RequestBody ControlRequest request,
                              @RequestHeader(name="Authorization") String jwt)   {
        Long userId = challengeService.authCheck(jwt); //recupero id di chi valuta e verifico token
        return challengeService.userChallengeOperation(request,userId);
    }


}