package br.com.stark_bank.application.dtos;

import com.starkbank.Invoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDTO {

    private Long amount;
    private String due;
    private String name;
    private String taxId;
    private Long expiration;
    private Integer fine;
    private Double interest;
    private String[] tags;
    private List<Invoice.Rule> rules;
    private List<Object> descriptions;
    private List<Object> discounts;
}
