package com.ead.authuser.configs;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import net.kaczmarzyk.spring.data.jpa.web.*;
import org.springframework.context.annotation.*;
import org.springframework.data.web.*;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.*;
import org.springframework.web.method.support.*;
import org.springframework.web.servlet.config.annotation.*;

import java.util.*;

@Configuration
public class ResolverConfig extends WebMvcConfigurationSupport {

    //Config format LocalDateTime
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(mapper);
        converters.add(jsonConverter);

        super.configureMessageConverters(converters);
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){

        argumentResolvers.add(new SpecificationArgumentResolver());

        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }
} 
