package com.hacom.controller;

import com.hacom.model.DateRangeRequest;
import com.hacom.model.TraceMsg;
import com.hacom.service.TraceMsgService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/traces")
@RequiredArgsConstructor
public class TraceMsgController {
    private static final Logger logger = LogManager.getLogger(TraceMsgController.class);
    private final TraceMsgService service;

    @PostMapping
    public Mono<TraceMsg> insertTrace(@RequestBody TraceMsg traceMsg) {
        logger.info("TraceMsgController --> insertTrace: {}", traceMsg);
        return service.insertTrace(traceMsg).doOnSuccess(t -> logger.info("Guardado exitosamente: {}", t));
    }

    @GetMapping
    public Flux<TraceMsg> getTracesByDateRange(@RequestBody @Valid DateRangeRequest request) {
        logger.info("TraceMsgController --> getTracesByDateRange");
        return service.getTracesByDateRange(request);
    }
}
