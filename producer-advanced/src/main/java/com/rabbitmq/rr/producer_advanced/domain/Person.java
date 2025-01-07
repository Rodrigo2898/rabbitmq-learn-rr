package com.rabbitmq.rr.producer_advanced.domain;

import java.time.LocalDate;

public record Person(String name,
                     Integer collageCompletedYear,
                     LocalDate bornAt,
                     Boolean active) {
}
