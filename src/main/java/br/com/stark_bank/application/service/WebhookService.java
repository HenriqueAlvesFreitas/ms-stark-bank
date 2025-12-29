package br.com.stark_bank.application.service;

import br.com.stark_bank.application.dtos.TransferDTO;
import br.com.stark_bank.application.dtos.eventDTO.EventWrapperDTO;
import br.com.stark_bank.infra.RestResponse;
import br.com.stark_bank.application.service.starkBank.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.starkbank.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class WebhookService {

    private final TransferService transferService;

    public WebhookService(
            TransferService transferService) {

        this.transferService = transferService;
    }


    public ResponseEntity<RestResponse<Object>> receiveEvent(EventWrapperDTO event) {

        log.info("ReceiveEvent: {}", event);

        if(Objects.equals(event.getEvent().getLog().getType(), "credited")){
            Long amount = event.getEvent().getLog().getInvoice().getNominalAmount() - event.getEvent().getLog().getInvoice().getDiscountAmount();

            createTransfer(amount);
        }

        return RestResponse.success(event, "Information returned successfully");

    };

    private void createTransfer(Long amount){
        List<TransferDTO> transferDTOList = new ArrayList<>();

        TransferDTO transferDTO = TransferDTO.builder()
                .amount(amount)
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
            log.info("CreateTransfer: {}", transfer);
        }
    }

}
