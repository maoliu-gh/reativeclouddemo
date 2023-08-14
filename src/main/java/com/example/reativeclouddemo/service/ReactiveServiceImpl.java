package com.example.reativeclouddemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ReactiveServiceImpl implements ReactiveService{

	private final Source source;
	@Autowired
	public ReactiveServiceImpl(Source source) {
		this.source = source;
	}
	@Override
	public void sendMessage(String message) {
		MessageChannel msgChannel = source.output();
		msgChannel.send(MessageBuilder.withPayload(message).build());
	}

	

}
