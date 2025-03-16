package com.hacom.service.impl;

import com.hacom.model.DateRangeRequest;
import com.hacom.model.TraceMsg;
import com.hacom.repository.TraceMsgRepository;
import com.hacom.service.TraceMsgService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class TraceMsgServiceImpl implements TraceMsgService {
    private final TraceMsgRepository repository;
    private final Counter insertCounter;

    @Autowired
    public TraceMsgServiceImpl(TraceMsgRepository repository, MeterRegistry meterRegistry) {
        this.repository = repository;
        this.insertCounter = meterRegistry.counter("hacom.test.developer.insert.rx");
    }

    @Override
    public Mono<TraceMsg> insertTrace(TraceMsg traceMsg) {
        insertCounter.increment();
        return repository.save(traceMsg);
    }

    @Override
    public Flux<TraceMsg> getTracesByDateRange(DateRangeRequest request) {
        return repository.findByTsBetween(request.getFrom(), request.getTo());
    }
}

