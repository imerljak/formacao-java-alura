package br.com.casadocodigo.loja.infra;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StringToCalendarConverter implements Converter<String, Calendar> {

    private final SimpleDateFormat dateFormat;

    public StringToCalendarConverter(String pattern) {
        this.dateFormat = new SimpleDateFormat(pattern);
    }

    @Override
    public Calendar convert(String s) {

        if (StringUtils.isEmpty(s)) {
            return null;
        }

        try {
            Calendar instance = Calendar.getInstance();
            instance.setTime(dateFormat.parse(s));
            return instance;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
