package br.com.stark_bank.application.service.starkBank;

import br.com.stark_bank.infra.StarkBank.ProjectFactory;
import com.starkbank.Settings;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class StarkBankInitializer {

    private final ProjectFactory projectFactory;

    public StarkBankInitializer(ProjectFactory projectFactory) {
        this.projectFactory = projectFactory;
    }

    @PostConstruct
    public void init() {

        Settings.user = projectFactory.create();
    }
}
