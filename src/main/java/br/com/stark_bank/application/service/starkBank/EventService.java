package br.com.stark_bank.application.service.starkBank;


import br.com.stark_bank.application.exceptions.StarkBankException;
import com.starkbank.Event;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    public Event createEvent(String content, String signature){
        try{
            return Event.parse(content, signature);
        }catch (Exception e){
            throw new StarkBankException(
                    "Error creating event",
                    e
            );
        }
    }
}
