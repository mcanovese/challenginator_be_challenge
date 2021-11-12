package com.synclab.Challenginator.microservice.challenge.challenge;

import com.synclab.Challenginator.microservice.challenge.insertChallenge.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/*
 REST controller del microservizio
 l'inserimento è delegato ad un controller specifico
 */

@RestController
public class ChallengeController {

    private InsertService insertService;

    private ChallengeService challengeService;

    @Autowired
    private RestTemplate restTemplate;

    public ChallengeController(ChallengeService challengeService ) {
        this.challengeService = challengeService;
    }

    //CHALLENGE POST INSERIMENTO delegata ad inset

    //restituisce tutte le challenge
    @GetMapping("/challenge")
    public List<Challenge> getChallenge(@RequestHeader(name="Authorization") String jwt)   {
       Long userId =  challengeService.authCheck(jwt);
        return challengeService.getALlChallenge(userId);
    }

    //get di una challenge
    @GetMapping("/challenge/{id}")
    public Challenge getChallengeById(@PathVariable Long id, @RequestHeader(name="Authorization") String jwt)
           {
        challengeService.authCheck(jwt);
        return challengeService.getChallengeById(id);
    }

    //aggiorna challenge
    @PutMapping("/challenge")
    public HttpStatus updateChallenge(@RequestBody Challenge challenge, @RequestHeader(name="Authorization") String jwt)
             {
        challengeService.authCheck(jwt);
        Boolean result =  challengeService.updateChallenge(challenge);
        if (result) return HttpStatus.OK;
        else return HttpStatus.BAD_REQUEST;
    }

    //cancella challenge con ID
    @DeleteMapping("/challenge/{id}")
    public HttpStatus deleteChallenge(@PathVariable Long id,@RequestHeader(name="Authorization") String jwt)   {
        Long userId = challengeService.authCheck(jwt);
        return challengeService.deleteChallenge(id, userId);

    }

    //Valutazione da parte del valutatore
    @PostMapping("/challenge/evaluate")
    public Challenge evaluate(@RequestBody ValutatorRequest request,
                           @RequestHeader(name="Authorization") String jwt)  {
        Long idValutatore = challengeService.authCheck(jwt); //recupero id di chi valuta e verifico token
       return challengeService.evaluateChallenge(request,idValutatore);
    }

    //restituische le challenge da valutare per l'utente in qualità di giudice
    @GetMapping("/challenge/evaluate")
    public List<Challenge> getChallengeEvaluate( @RequestHeader(name="Authorization") String jwt)
    {
        Long userId = challengeService.authCheck(jwt);
        return challengeService.getChallengeToEvaluate(userId);
    }


    //operazioni utente sulla challenge, accetta e/o rifiuta in base al contenuto della request
    @PostMapping("/challenge/control")
    public Challenge control(@RequestBody ControlRequest request,
                              @RequestHeader(name="Authorization") String jwt)   {
        Long userId = challengeService.authCheck(jwt); //recupero id di chi valuta e verifico token
        return challengeService.userChallengeOperation(request,userId);
    }


}