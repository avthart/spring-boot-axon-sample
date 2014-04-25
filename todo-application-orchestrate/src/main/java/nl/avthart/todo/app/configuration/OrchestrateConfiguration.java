package nl.avthart.todo.app.configuration;

import io.orchestrate.client.Client;
import io.orchestrate.client.OrchestrateClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrchestrateConfiguration {

	@Bean
	public Client client() {
		return new OrchestrateClient("");	
	}
}
