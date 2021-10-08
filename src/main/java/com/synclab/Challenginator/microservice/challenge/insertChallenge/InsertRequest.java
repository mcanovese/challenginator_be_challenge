package com.synclab.Challenginator.microservice.challenge.insertChallenge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;



/*
 Inserimento di una nuova sfida, l'utente che la invia sta sfidando un terzo
 */

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class InsertRequest {

    private final String title;
    private final String description;
    private final Long deadline;
    private final Long idChallenged;




}
