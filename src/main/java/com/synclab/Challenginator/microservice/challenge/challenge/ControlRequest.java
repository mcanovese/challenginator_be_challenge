package com.synclab.Challenginator.microservice.challenge.challenge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ControlRequest {

    private Long challengeId;
    private boolean accept;  // accetta sfida
    private boolean giveup; // arrenditi
    private boolean refuse; // rifiuta

}
