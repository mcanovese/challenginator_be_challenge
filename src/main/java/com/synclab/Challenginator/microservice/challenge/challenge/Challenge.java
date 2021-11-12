package com.synclab.Challenginator.microservice.challenge.challenge;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;



/*
Definizione della Challenge
 */

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Challenge {

    @Id  //PK
    @SequenceGenerator(
            name = "challenge_sequence",
            sequenceName = "challenge_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "challenge_sequence")
    private Long id;

    private LocalDateTime timestamp_creation;
    private Long challenger; //sfidante

    private Long challenged; //sfidato
    private String title;
    private String description;
    private Long deadline;

    @Enumerated(EnumType.STRING)
    private ChallengeStatus status;
    private LocalDateTime timestamp_acceptance;
    private Long evaluator;

    @Enumerated(EnumType.STRING)
    private ChallengeResult result;

    public Challenge(LocalDateTime timestamp_creation,
                     Long idChallenger,
                     Long idChallenged,
                     String title,
                     String description,
                     Long deadline,
                     ChallengeStatus status,
                     Long evaluator) {
        this.timestamp_creation = timestamp_creation;
        this.challenger = idChallenger;
        this.challenged = idChallenged;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.evaluator = evaluator;
    }
}
