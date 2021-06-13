package labs.redis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;

@Singleton
public class IncrementService {

	@Inject
	RedisClient redisClient;

	String get(String key) {
		return redisClient.get(key).toString();
	}

	void set(String key, Integer value) {
		redisClient.set(Arrays.asList(key, value.toString()));
	}

	void increment(String key, Integer incrementBy) {
		redisClient.incrby(key, incrementBy.toString());
	}

	void del(String key) {
		redisClient.del(Arrays.asList(key));
	}

	List<String> keys() {
		return redisClient.keys("*").stream().map(Response::toString).collect(Collectors.toList());
	}

}
