package br.com.stark_bank.application.controller;

import br.com.stark_bank.application.dtos.eventDTO.EventWrapperDTO;
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
    public ResponseEntity<RestResponse<Object>> receiveEvent(@RequestBody EventWrapperDTO event, @RequestHeader("Digital-Signature") String signature){
        return service.receiveEvent(event);
    }

}
