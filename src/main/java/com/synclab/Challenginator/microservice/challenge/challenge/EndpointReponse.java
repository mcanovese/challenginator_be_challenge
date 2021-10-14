package com.synclab.Challenginator.microservice.challenge.challenge;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class EndpointReponse {

    private String responseType;
    private String description;

}
