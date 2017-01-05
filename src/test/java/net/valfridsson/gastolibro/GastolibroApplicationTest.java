package net.valfridsson.gastolibro;

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class GastolibroApplicationTest {

    @ClassRule
    public static final DropwizardAppRule<GastolibroConfiguration> RULE =
        new DropwizardAppRule<>(GastolibroApplication.class, "src/test/resources/gastolibro-test.yml");

    @Test
    public void applicationServesDocumentation() {
        Client client = ClientBuilder.newClient();

        Response response = client.target(String.format("http://localhost:%d/admin/healthcheck", RULE.getLocalPort()))
            .request()
            .get();

        assertThat(response.getStatus()).isEqualTo(200);
    }
}