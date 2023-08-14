package com.example.reativeclouddemo.controller;


import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.reativeclouddemo.service.ReactiveService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Flux;
//Test
@RestController
public class KafkaController {
 private final ReactiveService reactiveService;
 private volatile Flux<String> messageStream;

@Autowired
public KafkaController(ReactiveService reactiveService) {
	this.reactiveService = reactiveService;
}

@PostMapping ("/sendmsg")
@ApiOperation ("Send Message")
public void sendMessage (@RequestBody String message)throws IOException {
	reactiveService.sendMessage(message);
}

@StreamListener
public void messageListener (@Input(Sink.INPUT)Flux<String> input) {
	input.subscribe(m->messageStream = input.map(s->s.toUpperCase()));
	//input.subscribe(m->m.toUpperCase());
	
}

@GetMapping ("/getMessage")
@ApiOperation("Get Message")
public Flux<String> getMessage(){
	return messageStream;
}

@StreamEmitter
@Output(Source.OUTPUT)
public Flux<String> messageEmitter () {
	return Flux.interval(Duration.ofSeconds(3)).map(s->s.toString());
	
}
}
