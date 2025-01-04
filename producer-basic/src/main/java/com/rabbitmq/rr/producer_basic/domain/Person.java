package com.rabbitmq.rr.producer_basic.domain;

import java.time.LocalDate;

public record Person(String name,
                     Integer collageCompletedYear,
                     LocalDate bornAt,
                     Boolean active) {
}
