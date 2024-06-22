package com.imdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocalizationResolver {
    private final ApplicationContext applicationContext;

    @Autowired
    public LocalizationResolver(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String resolve(String key) {
        String keyProcess = key.replace("{", "").replace("}", "");
        return keyProcess == null ? "" : this.applicationContext.getMessage(keyProcess, (Object[]) null, LocaleContextHolder.getLocale());
    }
}