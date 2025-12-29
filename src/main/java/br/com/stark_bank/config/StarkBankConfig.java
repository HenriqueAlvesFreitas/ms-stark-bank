package br.com.stark_bank.config;

import br.com.stark_bank.infra.StarkBank.ProjectFactory;
import com.starkbank.Settings;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("!test")
public class StarkBankConfig {

    private final ProjectFactory projectFactory;

    public StarkBankConfig(ProjectFactory projectFactory) {
        this.projectFactory = projectFactory;
    }

    @PostConstruct
    public void init() throws Exception {
        try {
            Settings.user = projectFactory.create();

            com.starkbank.Balance.get();

            log.info("StarkBankInitializer: project validated and ready");
        } catch (Exception e) {
            log.error("Failed to initialize StarkBank", e);
            throw e;
        }
    }
}
