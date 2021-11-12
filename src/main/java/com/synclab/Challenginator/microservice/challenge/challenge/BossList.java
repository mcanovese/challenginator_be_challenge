package com.synclab.Challenginator.microservice.challenge.challenge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;



/*
classe che gestisce il ritorno della gerarchia  dei superiori di un utente
server per determinare il valutatore di una sfida
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BossList {
    private Long bossOfUserBoss;
    private Long userId;
    private Long bossOfUser;


    public Long getBossOfUserBoss() {
        return bossOfUserBoss;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBossOfUser() {
        return bossOfUser;
    }
}


