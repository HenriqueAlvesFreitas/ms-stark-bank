package br.com.stark_bank.application.service.starkBank;

import br.com.stark_bank.application.dtos.TransferDTO;
import br.com.stark_bank.application.dtos.mappers.TransferMapper;
import br.com.stark_bank.application.exceptions.StarkBankException;
import br.com.stark_bank.application.port.StarkBankPort;
import com.starkbank.Transfer;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class TransferServiceTest {

    @Mock
    private StarkBankPort starkBank;

    @Mock
    TransferMapper transferMapper;

    @Autowired
    @InjectMocks
    @Spy
    private TransferService transferService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create the transfer")
    void createTransferSuccessfully() {
        TransferDTO dto = mock(TransferDTO.class);

        Map<String, Object> map = new HashMap<>();
        map.put("amount", 1L);
        map.put("name", "Buzz Aldrin");

        when(transferMapper.toMap(dto)).thenReturn(map);

        Transfer transfer = transferService.createTransfer(dto);

        assertNotNull(transfer);
        verify(transferMapper).toMap(dto);
    }

    @Test
    @DisplayName("Should not create the transfeer")
    void createTransferError() {
        TransferDTO dto = TransferDTO.builder()
                .amount(1L)
                .name("Buzz Aldrin")
                .build();

        RuntimeException originalException = new RuntimeException("Mapper error");

        when(transferMapper.toMap(dto))
                .thenThrow(originalException);

        StarkBankException exception = assertThrows(
                StarkBankException.class,
                () -> transferService.createTransfer(dto)
        );

        assertEquals("Error creating transfer", exception.getMessage());
        assertEquals(originalException, exception.getCause());
    }

    @Test
    @DisplayName("Should save the transfers")
    void saveTransferListSuccessfully() {
        TransferDTO dto = mock(TransferDTO.class);
        Transfer transfer = mock(Transfer.class);

        List<TransferDTO> dtoList = List.of(dto);
        List<Transfer> transferList = List.of(transfer);

        doReturn(transfer)
                .when(transferService)
                .createTransfer(dto);

        when(starkBank.saveTransfers(transferList)).thenReturn(transferList);

        List<Transfer> result = transferService.saveTransferList(dtoList);

        assertEquals(1, result.size());
        assertEquals(transfer, result.getFirst());

        verify(transferService).createTransfer(dto);
        verify(starkBank).saveTransfers(transferList);
    }

    @Test
    @DisplayName("Should not save the transfers")
    void saveTransferListError() {
        TransferDTO dto = mock(TransferDTO.class);
        Transfer transfer = mock(Transfer.class);

        List<TransferDTO> dtoList = List.of(dto);
        List<Transfer> transferList = List.of(transfer);

        doReturn(transfer)
                .when(transferService)
                .createTransfer(dto);

        when(starkBank.saveTransfers(transferList))
                .thenThrow(new StarkBankException("Error saving transfers", any()));

        StarkBankException exception = assertThrows(
                StarkBankException.class,
                () -> transferService.saveTransferList(dtoList)
        );

        assertEquals("Error saving transfers", exception.getMessage());

        verify(transferService).createTransfer(dto);
        verify(starkBank).saveTransfers(transferList);
    }
}