package com.hacom.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Document(collection = "trace_messages")
public class TraceMsg {
    @Id
    private ObjectId _id;
    private String sessionId;
    private String payload;
    private Date ts;
    //private OffsetDateTime ts;
}