package com.shunya.jacksondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

@RestController
public class DateController {

    @Autowired
    private FooService fooService;

    @PostMapping("/date")
    public SampleDto create(@RequestBody SampleDto payload) {
        return payload;
    }

    @GetMapping("/date")
    public SampleDto get() {
        final SampleDto dto = new SampleDto();
        final Instant time = Instant.ofEpochMilli(1571884105000L);
        dto.setInstant(time);
        dto.setDate(new Date(time.toEpochMilli()));
        dto.setLocalDate(time.atZone(ZoneId.of("UTC")).toLocalDate());
        dto.setLocalDateTime(time.atZone(ZoneId.of("UTC")).toLocalDateTime());
        return dto;
    }
}
