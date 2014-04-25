package nl.avthart.todo.app.configuration;

import io.orchestrate.client.Client;

import java.util.Arrays;

import nl.avthart.axon.repository.orchestrate.OrchestrateRepository;
import nl.avthart.todo.app.domain.task.Task;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Axon Java Configuration with reasonable defaults like SimpleCommandBus, SimpleEventBus and Orchestrate Repository.
 * @author albert
 */
@Configuration
public class AxonConfiguration {
	
//	@Autowired
//	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private Client client;
	
	@Bean
	public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
		AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
		processor.setEventBus(eventBus());
		return processor;
	}
	
	@Bean
	public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
		AnnotationCommandHandlerBeanPostProcessor processor = new AnnotationCommandHandlerBeanPostProcessor();
		processor.setCommandBus(commandBus());
		return processor;
	}

	@Bean
	public CommandBus commandBus() {
		SimpleCommandBus commandBus = new SimpleCommandBus();
		commandBus.setHandlerInterceptors(Arrays.asList(new BeanValidationInterceptor()));
//		commandBus.setTransactionManager(new SpringTransactionManager(transactionManager));
		return commandBus;
	}

	@Bean
	public EventBus eventBus() {
		return new SimpleEventBus();
	}
	
	@Bean
	public CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean() {
		CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<CommandGateway>();
		factory.setCommandBus(commandBus());
		return factory;
	}

//	@Bean
//	public EntityManagerProvider entityManagerProvider() {
//		return new ContainerManagedEntityManagerProvider();
//	}
	
	@Bean
	public OrchestrateRepository<Task> taskRepository() {
		OrchestrateRepository<Task> repository = new OrchestrateRepository<Task>(client, "tasks", Task.class);
		repository.setEventBus(eventBus());
		return repository;
	}
	
	@Bean
	public AggregateAnnotationCommandHandler<Task> taskCommandHandler() {
		AggregateAnnotationCommandHandler<Task> commandHandler = AggregateAnnotationCommandHandler.subscribe(Task.class, taskRepository(), commandBus());
		return commandHandler;
	}
}
