package com.synclab.Challenginator.microservice.challenge.challenge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ControlRequest {

    private Long challengeId;
    private boolean accept;  // accetta sfida
    private boolean giveup; // arrenditi
    private boolean refuse; // rifiuta
    private boolean complete; //completa

    public boolean isComplete() {
        return complete;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public boolean isAccept() {
        return accept;
    }

    public boolean isGiveup() {
        return giveup;
    }

    public boolean isRefuse() {
        return refuse;
    }
}
