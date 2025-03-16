package com.hacom.repository;

import com.hacom.model.TraceMsg;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.OffsetDateTime;

@Repository
public interface TraceMsgRepository extends ReactiveMongoRepository<TraceMsg, ObjectId> {
    Flux<TraceMsg> findByTsBetween(OffsetDateTime from, OffsetDateTime to);
}