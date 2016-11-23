package com.jamasoftware.services.restclient.jamadomain.values;

import com.jamasoftware.services.restclient.exception.JamaTypeMismatchException;
import com.jamasoftware.services.restclient.exception.RestClientException;
import com.jamasoftware.services.restclient.util.DateUtil;

import java.text.ParseException;
import java.util.Date;

public class DateFieldValue extends JamaFieldValue {
    private Date value;

    @Override
    public Date getValue() {
        return value;
    }

    @Override
    public void setValue(String value) throws RestClientException {
        try {
            this.value = DateUtil.parseDate(value);
        } catch (ParseException e) {
            throw new RestClientException("Unable to parse date: " + value);
        }
    }

    public void setValue(Object value) throws JamaTypeMismatchException {
        checkType(Date.class, value);
        this.value = (Date) value;
    }
    public void setValue(Date value) {
        this.value = value;
    }
}
