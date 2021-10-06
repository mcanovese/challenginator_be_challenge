package com.synclab.Challenginator.microservice.challenge.insertChallenge;

import lombok.AllArgsConstructor;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/challenge")
@AllArgsConstructor
public class InsertController {


    private InsertService insertService;


    @PostMapping
    public String insert(@RequestBody InsertRequest request){
        return insertService.insert(request);

    }


}
