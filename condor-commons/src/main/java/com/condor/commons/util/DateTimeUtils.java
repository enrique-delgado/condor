package com.condor.commons.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtils {

    // Convierte LocalDateTime a long (epoch millis)
    public static long toEpochMillis(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    // Convierte long (epoch millis) a LocalDateTime
    public static LocalDateTime fromEpochMillis(long millis) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(millis),
                ZoneId.systemDefault()
        );
    }
}
