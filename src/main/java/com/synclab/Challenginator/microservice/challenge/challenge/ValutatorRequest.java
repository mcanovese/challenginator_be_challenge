package com.synclab.Challenginator.microservice.challenge.challenge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ValutatorRequest {

    private Long challengeId;
    private ChallengeResult result;
}
