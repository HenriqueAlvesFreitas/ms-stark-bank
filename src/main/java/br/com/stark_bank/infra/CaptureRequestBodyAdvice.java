package br.com.stark_bank.infra;

import org.springframework.core.MethodParameter;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;


@ControllerAdvice
public class CaptureRequestBodyAdvice extends RequestBodyAdviceAdapter {

    private static final ThreadLocal<Object> requestBodyHolder = new ThreadLocal<>();

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
                                MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        requestBodyHolder.set(body);
        return body;
    }

    public static Object getRequestBody() {
        return requestBodyHolder.get();
    }

    public static void clear() {
        requestBodyHolder.remove();
    }
}

