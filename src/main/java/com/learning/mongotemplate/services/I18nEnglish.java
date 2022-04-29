package com.learning.mongotemplate.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("EN")
@Service("i18Service")
public class I18nEnglish implements GreetingService {
    @Override
    public String sayHello() {
        return "Hello User - EN";
    }
}
