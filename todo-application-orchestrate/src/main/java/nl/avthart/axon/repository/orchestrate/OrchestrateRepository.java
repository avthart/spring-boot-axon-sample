package nl.avthart.axon.repository.orchestrate;

import io.orchestrate.client.Client;
import io.orchestrate.client.KvMetadata;
import io.orchestrate.client.KvObject;
import io.orchestrate.client.ResponseAdapter;

import org.axonframework.domain.AggregateRoot;
import org.axonframework.repository.AbstractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrchestrateRepository<T extends AggregateRoot<?>> extends AbstractRepository<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrchestrateRepository.class);
	
	private Client client;
	
	private String collection;
	
	private Class<T> aggregateType;

	public OrchestrateRepository(Client client, String collection, Class<T> aggregateType) {
		super(aggregateType);
		this.client = client;
		this.collection = collection;
		this.aggregateType = aggregateType;
	}

	@Override
	protected void doDelete(T aggregate) {
		// TODO handle
	}

	@Override
	protected T doLoad(Object id, Long version) {
		LOGGER.info("load aggregate id = {}, version = {}", id, version);
		// TODO handle version
		String key = id.toString();
		KvObject<T> aggregate = client.kv(collection, key)
		.get(aggregateType)
		.get();
		
		if(aggregate == null) {
			// TODO handle
		}
		return aggregate.getValue();
	}

	@Override
	protected void doSave(T aggregate) {
		LOGGER.info("save aggregate id = {}", aggregate.getIdentifier());
		final String key = aggregate.getIdentifier().toString();
		client.kv(collection, key)
		.put(aggregate)
		.on(new ResponseAdapter<KvMetadata>() {
			@Override
			public void onFailure(Throwable error) {
				LOGGER.info("error while saving aggregate id = " + key, error);
			}
			@Override
			public void onSuccess(KvMetadata object) {
				LOGGER.info("aggregate id {} saved succesfully", key);
			}
		});		
	}
}
