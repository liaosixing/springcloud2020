package com.atguigu.springcloud.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.atguigu.springcloud.service.IMessageProvider;

@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider {

	@Resource(name = "output")
	private MessageChannel channel;
	@Override
	public String send() {
		String serial = UUID.randomUUID().toString();
		channel.send(MessageBuilder.withPayload(serial).build());
		System.out.println("------serial:"+serial);
		return serial;
	}

}
