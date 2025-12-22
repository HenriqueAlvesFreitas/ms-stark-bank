package br.com.stark_bank.application.port;

import com.starkbank.Invoice;
import com.starkbank.Transfer;

import java.util.List;

public interface StarkBankPort {

    List<Invoice> saveInvoices(List<Invoice> invoices);

    List<Transfer> saveTransfers(List<Transfer> transfers);
}
