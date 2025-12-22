package br.com.stark_bank.application.dtos.mappers;

import br.com.stark_bank.application.dtos.InvoiceDTO;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    default Map<String, Object> toMap(InvoiceDTO dto) {
        Map<String, Object> data = new HashMap<>();

        data.put("amount", dto.getAmount());
        data.put("due", dto.getDue());
        data.put("name", dto.getName());
        data.put("taxId", dto.getTaxId());
        data.put("expiration", dto.getExpiration());
        data.put("fine", dto.getFine());
        data.put("interest", dto.getInterest());
        data.put("tags", dto.getTags());
        data.put("rules", dto.getRules());
        data.put("descriptions", dto.getDescriptions());

        return data;
    }
}
