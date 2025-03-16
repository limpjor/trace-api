package com.hacom.config;

import org.bson.*;
import org.bson.codecs.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateTimeCodec implements Codec<OffsetDateTime> {

    @Override
    public void encode(BsonWriter writer, OffsetDateTime value, org.bson.codecs.EncoderContext encoderContext) {
        writer.writeDateTime(value.toInstant().toEpochMilli()); // Convertir a millis
    }

    @Override
    public OffsetDateTime decode(BsonReader reader, org.bson.codecs.DecoderContext decoderContext) {
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(reader.readDateTime()), ZoneOffset.UTC);
    }

    @Override
    public Class<OffsetDateTime> getEncoderClass() {
        return OffsetDateTime.class;
    }

}
