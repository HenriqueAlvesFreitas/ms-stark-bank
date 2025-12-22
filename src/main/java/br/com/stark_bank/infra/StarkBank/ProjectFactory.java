package br.com.stark_bank.infra.StarkBank;

import br.com.stark_bank.application.exceptions.StarkBankException;
import com.starkbank.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProjectFactory {

    @Value("${app.starkbank.private-key}")
    private String privateKey;

    @Value("${app.starkbank.project-id}")
    private String projectId;

    @Value("${app.starkbank.env}")
    private String enviroment;

    private String normalizePrivateKey(String key) {
        return key.replace("\\n", "\n");
    }

    public Project create() {
        try {
            return new Project(
                    enviroment,
                    projectId,
                    normalizePrivateKey(privateKey)
            );
        } catch (Exception e) {
            throw new StarkBankException(
                    "Error initializing stark bank project",
                    e
            );
        }
    }

}


