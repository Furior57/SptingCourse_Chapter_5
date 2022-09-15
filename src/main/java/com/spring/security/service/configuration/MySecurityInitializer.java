package com.spring.security.service.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
// Так как мы используем SpringMVC нам достаточно просто расширить класс
// AbstractSecurityWebApplicationInitializer. Все за что отвечает этот класс, это окошко с
// введением логина и пароля. А если поподробней, то он гарантирует, что конфигурация
// SpringSecurity будет загружена раньше конфигурации диспетчера сервлетов.
// Теперь создадим саму конфигурацию SpringSecurity, назовем MySecurityConfig, перейдем туда.
public class MySecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
