package com.synclab.Challenginator.microservice.challenge.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.naming.ldap.Control;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {


    private  ChallengeRepository challengeRepository;


    @Autowired
    private RestTemplate restTemplate;


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

    public Challenge getChallengeById(Long id){
        return challengeRepository.getChallengeById(id);
    }

    public Challenge updateChallenge(Challenge challenge){
        return challengeRepository.save(challenge);
    }

    public String insertNewChallenge(Challenge challenge){
        challengeRepository.save(challenge);

        return "success - challenge inserita";
    }

    public String evaluateChallenge(ValutatorRequest request, Long valutator){
        Challenge challengeToEvaluate = getChallengeById(request.getChallengeId());
        //verifica valutatore
        if(challengeToEvaluate.getEvaluator() == valutator){
            challengeToEvaluate.setResult(request.getResult());
            updateChallenge(challengeToEvaluate);
            return "evaluation ok";
        } else return "error valuator id";

    }

    public String userChallengeOperation(ControlRequest request, Long userId){
        Challenge challengeToOperate = getChallengeById(request.getChallengeId());

        if(challengeToOperate.getChallenged() != userId) return "Error - only challenged can operate";

        if(request.isAccept()){
            challengeToOperate.setTimestamp_acceptance(LocalDateTime.now());
            challengeToOperate.setStatus(ChallengeStatus.ACCEPTED);
            updateChallenge(challengeToOperate);
            return "challenge accepted";
        }

        if(request.isGiveup()){
            challengeToOperate.setResult(ChallengeResult.GIVEUP);
            challengeToOperate.setStatus(ChallengeStatus.TERMINATED);
            updateChallenge(challengeToOperate);
            return"give up";
        }

        if(request.isRefuse()){
            challengeToOperate.setStatus(ChallengeStatus.REFUSED);
            updateChallenge(challengeToOperate);
            return"challenge refused";
        } else return "ERROR - no operation selected";

    }


    public void deleteChallenge(Long id){
        challengeRepository.deleteById(id);
    }

    public Long authCheck(String jwt) throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwt);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Contatto il servizio utenti ed ottengo JSON con dettagli utente, se jwt is valid
        Long response = null;
        try{
            response = restTemplate.exchange("http://localhost:8080/user/authcheck",
                    HttpMethod.POST, entity, Long.class).getBody();
        } catch (HttpStatusCodeException exception){
            int statusCode = exception.getStatusCode().value();
           throw new Exception("Error - JWT is not valid or user service isn't up");
        }

        if (response == null)  throw new Exception("Error - JWT is not valid or user service isn't up");
        else return response;

    }


    // TEST TEST TEST TEST
    public String authCheckTest(String jwt){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwt);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Contatto il servizio utenti ed ottengo JSON con dettagli utente, se jwt is valid
        String response = null;
        try{
            response = restTemplate.exchange("http://localhost:8080/user/authcheck",
                    HttpMethod.POST, entity, String.class).getBody();
        } catch (HttpStatusCodeException exception){
            int statusCode = exception.getStatusCode().value();
            return "{\"response\":\"jwt is not valid or user service isn't up \" }";
        }

        if (response.isEmpty())   return "{\"response\":\"jwt is not valid or user service isn't up\" }";
        else  return "{\"response\":\"ok\" }";

    }
}
