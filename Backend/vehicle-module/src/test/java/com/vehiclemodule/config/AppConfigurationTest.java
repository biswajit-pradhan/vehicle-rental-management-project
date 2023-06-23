package com.vehiclemodule.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;

class AppConfigurationTest {

    @Test
    void testRestTemplate() {

        RestTemplate actualRestTemplateResult = (new AppConfiguration()).restTemplate();
        List<ClientHttpRequestInitializer> clientHttpRequestInitializers = actualRestTemplateResult
                .getClientHttpRequestInitializers();
        assertTrue(clientHttpRequestInitializers.isEmpty());
        UriTemplateHandler uriTemplateHandler = actualRestTemplateResult.getUriTemplateHandler();
        assertTrue(uriTemplateHandler instanceof DefaultUriBuilderFactory);
        assertTrue(actualRestTemplateResult.getRequestFactory() instanceof SimpleClientHttpRequestFactory);
        assertTrue(actualRestTemplateResult.getErrorHandler() instanceof DefaultResponseErrorHandler);
        List<HttpMessageConverter<?>> messageConverters = actualRestTemplateResult.getMessageConverters();
        assertEquals(7, messageConverters.size());
        assertEquals(clientHttpRequestInitializers, actualRestTemplateResult.getInterceptors());
        assertTrue(((DefaultUriBuilderFactory) uriTemplateHandler).getDefaultUriVariables().isEmpty());
        assertEquals(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT,
                ((DefaultUriBuilderFactory) uriTemplateHandler).getEncodingMode());
        assertEquals(1, messageConverters.get(2).getSupportedMediaTypes().size());
        assertEquals(2, messageConverters.get(1).getSupportedMediaTypes().size());
        assertEquals(6, ((AllEncompassingFormHttpMessageConverter) messageConverters.get(4)).getPartConverters().size());
        assertFalse(((Jaxb2RootElementHttpMessageConverter) messageConverters.get(5)).isSupportDtd());
        assertFalse(((Jaxb2RootElementHttpMessageConverter) messageConverters.get(5)).isProcessExternalEntities());
        ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) messageConverters.get(6)).getObjectMapper();
        assertNull(objectMapper.getPropertyNamingStrategy());
        assertTrue(objectMapper.getPolymorphicTypeValidator() instanceof LaissezFaireSubTypeValidator);
        assertSame(objectMapper.getJsonFactory(), objectMapper.getFactory());
        assertTrue(objectMapper.getDeserializationContext() instanceof DefaultDeserializationContext.Impl);
        assertTrue(objectMapper.getDateFormat() instanceof StdDateFormat);
        assertTrue(objectMapper.getSubtypeResolver() instanceof StdSubtypeResolver);
        assertTrue(objectMapper.getSerializerFactory() instanceof BeanSerializerFactory);
        assertTrue(objectMapper.getSerializerProviderInstance() instanceof DefaultSerializerProvider.Impl);
        assertTrue(objectMapper.getVisibilityChecker() instanceof VisibilityChecker.Std);
        assertTrue(objectMapper.getSerializerProvider() instanceof DefaultSerializerProvider.Impl);
    }
}

