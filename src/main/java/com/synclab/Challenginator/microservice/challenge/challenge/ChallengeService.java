package com.synclab.Challenginator.microservice.challenge.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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


    public Boolean authCheck(String jwt) throws Exception{

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
           throw new Exception("Error - JWT is not valid or user service isn't up");
        }

        if (response.isEmpty())  throw new Exception("Error - JWT is not valid or user service isn't up");
        else return true;

    }






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
