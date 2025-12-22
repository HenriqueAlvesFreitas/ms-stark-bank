package br.com.stark_bank.application.service.starkBank;

import br.com.stark_bank.application.dtos.TransferDTO;
import br.com.stark_bank.application.dtos.mappers.TransferMapper;
import br.com.stark_bank.application.exceptions.StarkBankException;
import br.com.stark_bank.application.port.StarkBankPort;
import com.starkbank.Transfer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {

    private final StarkBankPort starkBank;

    private final TransferMapper transferMapper;

    public TransferService(StarkBankPort starkBank, TransferMapper transferMapper){
        this.starkBank = starkBank;
        this.transferMapper = transferMapper;
    }

    public Transfer createTransfer(TransferDTO transfer){
        try{
            return new Transfer(transferMapper.toMap(transfer));
        }catch (Exception e){
            throw new StarkBankException(
                    "Error creating transfer",
                    e
            );
        }
    }

    public List<Transfer> saveTransferList(List<TransferDTO> transferDTOList){
        List<Transfer> transferList = new ArrayList<>();

        for(TransferDTO transfer : transferDTOList){
            transferList.add(createTransfer(transfer));
        }

        return starkBank.saveTransfers(transferList);
    }
}
