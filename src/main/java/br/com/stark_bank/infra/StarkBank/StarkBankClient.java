package br.com.stark_bank.infra.StarkBank;

import br.com.stark_bank.application.exceptions.StarkBankException;
import br.com.stark_bank.application.port.StarkBankPort;
import com.starkbank.Invoice;
import com.starkbank.Transfer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StarkBankClient implements StarkBankPort {


    @Override
    public List<Invoice> saveInvoices(List<Invoice> invoiceList) {
        try{
            return Invoice.create(invoiceList);
        } catch (Exception e) {
            throw new StarkBankException(
                    "Error saving invoices",
                    e
            );
        }
    }

    @Override
    public List<Transfer> saveTransfers(List<Transfer> transferList) {
        try{
            return Transfer.create(transferList);
        } catch (Exception e) {
            throw new StarkBankException(
                    "Error saving transfers",
                    e
            );
        }
    }
}
