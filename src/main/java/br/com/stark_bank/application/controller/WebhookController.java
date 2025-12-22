package br.com.stark_bank.application.controller;

import br.com.stark_bank.infra.RestResponse;
import br.com.stark_bank.application.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("webhook")
public class WebhookController {

    @Autowired
    WebhookService service;

    @PostMapping("/invoice")
    public ResponseEntity<RestResponse<Object>> save(@RequestBody String rawBody,  @RequestHeader("Digital-Signature") String signature){
        return service.save(rawBody, signature);
    }

    @GetMapping("/transfer")
    public void transfer(){
        service.transfer();
    }

}
