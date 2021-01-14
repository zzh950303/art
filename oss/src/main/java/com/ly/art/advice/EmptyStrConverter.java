package com.ly.art.advice;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class EmptyStrConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text))
            setValue(null);
        else
            setValue(text);
    }
}
