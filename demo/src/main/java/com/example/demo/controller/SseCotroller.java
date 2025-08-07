package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SseCotroller {
    @PostMapping("/publisher/{id}")
    public void publisher(@PathVariable("id") String id,@RequestParam("content") String content) {
        SseEmitter emitter = pool.get(id);
        if (Objects.isNull(emitter)) {
            return;
        }
        try {
            emitter.send(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private final Map<String, SseEmitter> pool = new ConcurrentHashMap<>();
    @GetMapping("/publisher/{id}")
    public SseEmitter subscribe(@PathVariable("id") String id) {
        SseEmitter emitter = pool.get(id);
        if (Objects.isNull(emitter)) {
            emitter = new SseEmitter();
            emitter.onCompletion(() -> pool.remove(id));
            emitter.onTimeout(() -> pool.remove(id));
            pool.put(id, emitter);
        }
        return emitter;
    }
}
