package com.synclab.Challenginator.microservice.challenge.insertChallenge;

import com.synclab.Challenginator.microservice.challenge.challenge.ChallengeService;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/challenge")
@AllArgsConstructor
public class InsertController {


    private InsertService insertService;
    private ChallengeService challengeService;


    @PostMapping
    public String insert(@RequestBody InsertRequest request, @RequestHeader(name="Authorization") String jwt) throws Exception{
        Long userid = challengeService.authCheck(jwt);
        return insertService.insert(request,userid,jwt);

    }


}
