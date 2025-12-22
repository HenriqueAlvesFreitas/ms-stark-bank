package br.com.stark_bank.application.schedulers;

import br.com.stark_bank.application.service.starkBank.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
    @RequiredArgsConstructor
public class InvoiceScheduler {

    private final InvoiceService invoiceService;

    @Scheduled(fixedRate = 15 * 60 * 1000) // 15 minutes
    public void generateInvoices() {
        invoiceService.saveSampleInvoice();
    }
}
