package br.com.stark_bank.application.dtos.eventDTO;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class InvoiceEventDTO {

    private String id;

    private String name;

    private String taxId;

    private String status;

    private Long amount;

    private Long nominalAmount;

    private Integer discountAmount;

    private Double fine;

    private Long fineAmount;

    private Double interest;

    private Long interestAmount;

    private Double fee;

    private String displayDescription;

    private String reversalDisplayDescription;

    private Long expiration;

    private String brcode;

    private String link;

    private String pdf;

    private OffsetDateTime created;

    private OffsetDateTime updated;

    private OffsetDateTime due;

    private List<Object> descriptions;

    private List<Object> discounts;

    private List<Object> rules;

    private List<Object> splits;

    private List<String> tags;

    private Object metadata;

    private List<String> transactionIds;

}
