package org.example.javawebdemo.utils;

import lombok.Getter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.StandardCharsets;


public class ThymeleafUtil {
    @Getter
    private static final TemplateEngine engine;

    static {
        engine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        engine.setTemplateResolver(resolver);

    }

}
