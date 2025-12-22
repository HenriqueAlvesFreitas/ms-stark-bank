package br.com.stark_bank.application.service.starkBank;

import br.com.stark_bank.application.dtos.InvoiceDTO;
import br.com.stark_bank.application.dtos.mappers.InvoiceMapper;
import br.com.stark_bank.application.exceptions.StarkBankException;
import br.com.stark_bank.application.port.StarkBankPort;
import com.starkbank.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InvoiceServiceTest {

    @Mock
    private StarkBankPort starkBank;

    @Mock
    private InvoiceMapper invoiceMapper;

    @Autowired
    @InjectMocks
    @Spy
    private InvoiceService invoiceService;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create the invoice")
    void createInvoiceSuccessfully() {
        InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                .amount(1L)
                .due("2027-01-28T17:59:26.249976+00:00")
                .name("Buzz Aldrin")
                .taxId("816.733.760-02")
                .expiration(123456789L)
                .fine(5)
                .interest(2.5)
                .build();

        Map<String, Object> map = new HashMap<>();
        map.put("amount", 1L);
        map.put("name", "Buzz Aldrin");

        when(invoiceMapper.toMap(invoiceDTO)).thenReturn(map);

        Invoice invoice = invoiceService.createInvoice(invoiceDTO);

        //VALIDATIONS
        assertNotNull(invoice);
        verify(invoiceMapper).toMap(invoiceDTO);

    }

    @Test
    @DisplayName("Should not create the invoice")
    void createInvoiceError() {

        InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                .amount(1L)
                .name("Buzz Aldrin")
                .build();

        RuntimeException originalException = new RuntimeException("Mapper error");

        when(invoiceMapper.toMap(invoiceDTO))
                .thenThrow(originalException);


        StarkBankException exception = assertThrows(
                StarkBankException.class,
                () -> invoiceService.createInvoice(invoiceDTO)
        );

        assertEquals("Error creating invoice", exception.getMessage());
        assertEquals(originalException, exception.getCause());

    }

    @Test
    @DisplayName("Should save the invoices")
    void saveInvoiceListSuccessfully() {
        InvoiceDTO dto = mock(InvoiceDTO.class);
        Invoice invoice = mock(Invoice.class);

        List<InvoiceDTO> dtoList = List.of(dto);
        List<Invoice> invoiceList = List.of(invoice);

        doReturn(invoice)
                .when(invoiceService)
                .createInvoice(dto);

        when(starkBank.saveInvoices(invoiceList)).thenReturn(invoiceList);

        List<Invoice> result = invoiceService.saveInvoiceList(dtoList);

        assertEquals(1, result.size());
        assertEquals(invoice, result.getFirst());

        verify(invoiceService).createInvoice(dto);
        verify(starkBank).saveInvoices(invoiceList);
    }

    @Test
    @DisplayName("Should not save the invoices")
    void saveInvoiceListError() {

        InvoiceDTO dto = mock(InvoiceDTO.class);
        Invoice invoice = mock(Invoice.class);

        List<InvoiceDTO> dtoList = List.of(dto);
        List<Invoice> invoiceList = List.of(invoice);

        doReturn(invoice)
                .when(invoiceService)
                .createInvoice(dto);

        when(starkBank.saveInvoices(invoiceList))
                .thenThrow(new StarkBankException("Error saving invoices", any()));


        StarkBankException exception = assertThrows(
                StarkBankException.class,
                () -> invoiceService.saveInvoiceList(dtoList)
        );

        assertEquals("Error saving invoices", exception.getMessage());

        verify(invoiceService).createInvoice(dto);
        verify(starkBank).saveInvoices(invoiceList);
    }
}