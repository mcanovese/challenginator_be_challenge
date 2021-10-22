package com.synclab.Challenginator.microservice.challenge.challenge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ValutatorRequest {

    private Long challengeId;
    private ChallengeResult result;

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public ChallengeResult getResult() {
        return result;
    }

    public void setResult(ChallengeResult result) {
        this.result = result;
    }
}
