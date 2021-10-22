package com.synclab.Challenginator.microservice.challenge.insertChallenge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


/*
 Inserimento di una nuova sfida, l'utente che la invia sta sfidando un terzo
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class InsertRequest {

    private final String title;
    private final String description;
    private final Long deadline;
    private final Long idChallenged;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getDeadline() {
        return deadline;
    }

    public Long getIdChallenged() {
        return idChallenged;
    }
}
