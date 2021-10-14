package com.synclab.Challenginator.microservice.challenge.insertChallenge;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synclab.Challenginator.microservice.challenge.challenge.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class InsertService {

    private final ChallengeService challengeService;

    @Autowired
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    //dall'id dello sfidato determino il giudice
    public Long getEvaluator(Long challenged,String jwt) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwt);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        BossList response = null;
        response = restTemplate.exchange("http://localhost:8080/user/valutator/"+ challenged,
                HttpMethod.GET, entity, new ParameterizedTypeReference<BossList>() {
                }).getBody();

        return response.getBossOfUser();
    }




    public HttpStatus insert(InsertRequest request, Long userid, String jwt) throws JsonProcessingException {

        Long evaluator = this.getEvaluator(request.getIdChallenged(),jwt);

        return challengeService.insertNewChallenge(
                new Challenge(
                        LocalDateTime.now(),
                        userid,
                        request.getIdChallenged(),
                        request.getTitle(),
                        request.getDescription(),
                        request.getDeadline(),
                        ChallengeStatus.PENDING,
                        evaluator


                )
        );


    }
}
