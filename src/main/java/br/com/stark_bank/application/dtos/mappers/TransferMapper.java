package br.com.stark_bank.application.dtos.mappers;

import br.com.stark_bank.application.dtos.TransferDTO;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    default Map<String, Object> toMap(TransferDTO dto) {
        Map<String, Object> data = new HashMap<>();

        data.put("amount", dto.getAmount());
        data.put("bankCode", dto.getBankCode());
        data.put("branchCode", dto.getBranchCode());
        data.put("accountNumber", dto.getAccountNumber());
        data.put("taxId", dto.getTaxId());
        data.put("name", dto.getName());
        data.put("externalId", dto.getExternalId());
        data.put("scheduled", dto.getScheduled());
        data.put("tags", dto.getTags());
        data.put("rules", dto.getRules());
        data.put("accountType", dto.getAccountType());

        return data;
    }
}
