package com.synclab.Challenginator.microservice.challenge.challenge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class BossList {
    private Long bossOfUserBoss;
    private Long userId;
    private Long bossOfUser;

}
