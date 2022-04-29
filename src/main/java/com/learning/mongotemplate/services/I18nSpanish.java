package com.learning.mongotemplate.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("ES")
@Service("i18Service")
public class I18nSpanish implements GreetingService{
    @Override
    public String sayHello() {
        return "Hola Mondus - ES";
    }
}
