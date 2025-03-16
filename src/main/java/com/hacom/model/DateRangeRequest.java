package com.hacom.model;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
public class DateRangeRequest {
    @NotNull
    private OffsetDateTime from;
    @NotNull
    private OffsetDateTime to;
}

