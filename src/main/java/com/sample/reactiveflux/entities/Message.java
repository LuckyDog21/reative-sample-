package com.sample.reactiveflux.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Message {
    @Id
    private Long id;
    private String body;

    public Message(String body) {
        this.body = body;
    }
}
