package br.com.stark_bank.application.dtos.eventDTO;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class EventDTO {

    private String id;

    private String workspaceId;

    private String subscription;

    private OffsetDateTime created;

    private LogDTO log;


}



