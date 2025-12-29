package br.com.stark_bank.application.service.starkBank;

import br.com.stark_bank.application.dtos.InvoiceDTO;
import br.com.stark_bank.application.dtos.mappers.InvoiceMapper;
import br.com.stark_bank.application.exceptions.StarkBankException;
import br.com.stark_bank.application.port.StarkBankPort;
import br.com.stark_bank.application.util.CPFGenerator;
import br.com.stark_bank.application.util.NameGenerator;
import com.starkbank.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class InvoiceService {

    private final StarkBankPort starkBank;

    private final InvoiceMapper invoiceMapper;


    public InvoiceService(StarkBankPort starkBank, InvoiceMapper invoiceMapper){
        this.starkBank = starkBank;
        this.invoiceMapper = invoiceMapper;
    }

    public Invoice createInvoice(InvoiceDTO invoice){
        try{
            return new Invoice(invoiceMapper.toMap(invoice));
        }catch (Exception e){
            throw new StarkBankException(
                    "Error creating invoice",
                    e
            );
        }
    }

    public List<Invoice> saveInvoiceList(List<InvoiceDTO> invoiceDTOList){
        List<Invoice> invoiceList = new ArrayList<>();

        for(InvoiceDTO invoice : invoiceDTOList){
            invoiceList.add(createInvoice(invoice));
        }

        return starkBank.saveInvoices(invoiceList);
    }

    public void saveSampleInvoice(){
        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
        List<Invoice.Rule> rules = new ArrayList<>();
        String cpf = CPFGenerator.randomCPF();
        rules.add(new Invoice.Rule("allowedTaxIds", new String[] {cpf}));

        long amount = ThreadLocalRandom.current().nextLong(1, 1_000) * 100;

        InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                .amount(amount)
                .due("2026-12-30T17:59:26.249976+00:00")
                .name(NameGenerator.randomName())
                .taxId(cpf)
                .expiration(123456789L)
                .fine(5)
                .interest(2.5)
                .rules(rules)
                .tags(new String[] {"immediate"})
                .build();

        invoiceDTOList.add(invoiceDTO);

        List<Invoice> invoices = saveInvoiceList(invoiceDTOList);

        log.info("SaveSampleInvoice: {}", invoices);
    }
}
