package br.com.stark_bank.application.service;

import br.com.stark_bank.application.dtos.TransferDTO;
import br.com.stark_bank.application.service.starkBank.EventService;
import br.com.stark_bank.infra.RestResponse;
import br.com.stark_bank.application.service.starkBank.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.starkbank.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebhookService {

    private final TransferService transferService;
    private final EventService eventService;

    public WebhookService(
            TransferService transferService,
            EventService eventService) {

        this.transferService = transferService;
        this.eventService = eventService;
    }


    public ResponseEntity<RestResponse<Object>> save(String rawBody, String signature) {

        Event event = eventService.createEvent(rawBody, signature);
        System.out.println(event);

        return RestResponse.success(rawBody, "Informação voltada com sucesso");

    };

    public void transfer(){
        List<TransferDTO> transferDTOList = new ArrayList<>();

        TransferDTO transferDTO = TransferDTO.builder()
                .amount(1L)
                .bankCode("20018183")
                .branchCode("0001")
                .accountNumber("6341320293482496")
                .taxId("20.018.183/0001-80")
                .name("Stark Bank S.A.")
                .externalId("my-external-id")
                .accountType("payment")
                .build();

        transferDTOList.add(transferDTO);

       List<Transfer> transferList = transferService.saveTransferList(transferDTOList);

        for (Transfer transfer : transferList){
            System.out.println(transfer);
        }
    }

}
