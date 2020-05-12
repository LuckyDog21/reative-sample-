package com.sample.reactiveflux.repo;

import com.sample.reactiveflux.entities.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
}
