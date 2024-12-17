package com.busanit501.boot501.controller.formatter;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class LocalDateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String text, Locale locale){
        LocalDate localDate = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDate;
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        String formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
        return formatter;
    }
}