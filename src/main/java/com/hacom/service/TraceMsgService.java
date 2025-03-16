package com.hacom.service;

import com.hacom.model.DateRangeRequest;
import com.hacom.model.TraceMsg;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TraceMsgService {
    Mono<TraceMsg> insertTrace(TraceMsg traceMsg);
    Flux<TraceMsg> getTracesByDateRange(DateRangeRequest request);
}
