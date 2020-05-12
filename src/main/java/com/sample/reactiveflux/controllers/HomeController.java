package com.sample.reactiveflux.controllers;

import com.sample.reactiveflux.entities.Message;
import com.sample.reactiveflux.repo.MessageRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final MessageRepository messageRepo;

    public HomeController(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping(value = {"/", ""}, produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    private Flux<Message> list() {
        /* one way with Flux.zip
        Flux<Message> message = messageRepo.findAll();
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(message, interval).map(Tuple2::getT1);*/
        return messageRepo.findAll().delayElements(Duration.ofSeconds(3));

    }

    @GetMapping("/{id}")
    private Mono<Message> one(@PathVariable("id") Long id) {
        return messageRepo.findById(id);
    }

    @PostMapping("/create")
    private Mono<Message> create(@RequestParam("body") String body) {
        return messageRepo.save(new Message(body));
    }
}
