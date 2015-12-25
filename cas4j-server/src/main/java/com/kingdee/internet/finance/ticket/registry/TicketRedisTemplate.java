package com.kingdee.internet.finance.ticket.registry;

import org.jasig.cas.ticket.Ticket;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class TicketRedisTemplate extends RedisTemplate<String, Ticket> {

	public TicketRedisTemplate() {
		RedisSerializer<String> string = new StringRedisSerializer();
		JdkSerializationRedisSerializer jdk = new JdkSerializationRedisSerializer();
		setKeySerializer(string);
		setValueSerializer(jdk);
		setHashKeySerializer(string);
		setHashValueSerializer(jdk);
	}

	public TicketRedisTemplate(RedisConnectionFactory connectionFactory) {
		this();
		setConnectionFactory(connectionFactory);
		afterPropertiesSet();
	}
}
