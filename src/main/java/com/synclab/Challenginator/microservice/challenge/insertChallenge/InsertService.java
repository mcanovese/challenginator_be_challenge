package com.synclab.Challenginator.microservice.challenge.insertChallenge;


import com.synclab.Challenginator.microservice.challenge.challenge.Challenge;
import com.synclab.Challenginator.microservice.challenge.challenge.ChallengeResult;
import com.synclab.Challenginator.microservice.challenge.challenge.ChallengeService;
import com.synclab.Challenginator.microservice.challenge.challenge.ChallengeStatus;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class InsertService {

    private final ChallengeService challengeService;

    public String insert(InsertRequest request){


        return challengeService.insertNewChallenge(
                new Challenge(
                        LocalDateTime.now(),
                        112L,
                        request.getIdChallenged(),
                        request.getTitle(),
                        request.getDescription(),
                        request.getDeadline(),
                        ChallengeStatus.PENDING
                )
        );


    }
}
