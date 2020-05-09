package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.StepStatus;
import enums.StepsName;
import model.AccountRest;
import model.StepsRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.AccountService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableWebMvc
public class AccountController {

    @Autowired
    private AccountService arquivoService;

    @RequestMapping(path = "/find-account/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> ping(@PathVariable("name") String name) {
        AccountRest rest = arquivoService.buscar(name);
/*
        addStepRest(rest, StepsName.RISK, StepStatus.START);
        arquivoService.salvar(rest);
        AccountRest restback = arquivoService.buscar(name);
*/

        return new ResponseEntity(rest, HttpStatus.OK);
    }

    @RequestMapping(path = "/create-account", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody String request)  throws IOException {
        ObjectMapper mapper = new ObjectMapper();
         AccountRest accountRest = mapper.readValue(request,AccountRest.class);
         addStepRest(accountRest, StepsName.INCLUDE, StepStatus.START);
         arquivoService.salvar(accountRest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void addStepRest(AccountRest accountRest, StepsName stepsName,StepStatus stepStatus) {
        List<StepsRest> stepsList;

        if (accountRest.getSteps() == null) {
            stepsList = new ArrayList<StepsRest>();
        }else{
            stepsList = accountRest.getSteps();
        }

        StepsRest step = new StepsRest();
        step.setNameStep(stepsName.getDescricao());
        step.setStatus(stepStatus.getDescricao());
        stepsList.add(step);
        accountRest.setSteps(stepsList);
    }
}
