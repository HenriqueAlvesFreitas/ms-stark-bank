package br.com.stark_bank.application.dtos;

import com.starkbank.Transfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {

        private Long amount;
        private String bankCode;
        private String branchCode;
        private String accountNumber;
        private String taxId;
        private String name;
        private String externalId;
        private String accountType;
        private OffsetDateTime scheduled;
        private List<String> tags;
        private List<Transfer.Rule> rules;

}
