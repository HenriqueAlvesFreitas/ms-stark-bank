package br.com.stark_bank.application.dtos.eventDTO;

import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class LogDTO {
    private String authentication;

    private OffsetDateTime created;

    private List<Object> errors;

    private String id;

    private String type;

    private InvoiceEventDTO invoice;
}
