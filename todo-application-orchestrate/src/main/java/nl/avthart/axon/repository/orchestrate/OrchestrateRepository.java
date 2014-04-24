package nl.avthart.axon.repository.orchestrate;

import io.orchestrate.client.Client;
import io.orchestrate.client.KvMetadata;
import io.orchestrate.client.KvObject;
import io.orchestrate.client.ResponseAdapter;

import org.axonframework.domain.AggregateRoot;
import org.axonframework.repository.Repository;

public class OrchestrateRepository<T extends AggregateRoot<?>> implements Repository<T> {

	private Client client;
	
	private String collection;
	
	private Class<T> clazz;

	public OrchestrateRepository(Client client, String collection, Class<T> clazz) {
		this.client = client;
		this.collection = collection;
		this.clazz = clazz;
	}
	
	@Override
	public void add(T aggregate) {
		String key = aggregate.getIdentifier().toString();
		client.kv(collection, key)
		.put(aggregate)
		.on(new ResponseAdapter<KvMetadata>() {
			@Override
			public void onFailure(Throwable error) {
				// TODO handle
			}
			@Override
			public void onSuccess(KvMetadata object) {
				// TODO handle
			}
		});
	}

	@Override
	public T load(Object id) {
		String key = id.toString();
		KvObject<T> aggregate = client.kv(collection, key)
		.get(clazz)
		.get();
		
		if(aggregate == null) {
			// TODO handle
		}
		
		return aggregate.getValue();
	}

	@Override
	public T load(Object id, Long version) {
		// TODO handle version...
		return load(id);
	}
}
