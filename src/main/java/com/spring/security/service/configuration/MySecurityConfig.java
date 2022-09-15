package com.spring.security.service.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

// Так как у нас web приложение, то прописываем аннотацию EnableWebSecurity, таким образом
// мы пометили как класс конфигурации. Внутри прописываются логины, пароли и роли для
// работников нашей компании. Для этого наследуемся от класса WebSecurityConfigurerAdapter,
// этот способ устарел, но лектор объяснял именно его.
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    // Имплементируем один метод, внутри создаем объект класса UserBuilder и устанавливаем
    // шифрование пароля по умолчанию, мы видим, что этот метод тоже устарел,
    // здесь он нам нужен для того, чтобы создавать пользователей и пароли прямо из программы,
    // в дальнейшем мы будем хранить их в базе данных в шифрованном виде.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        // А из названия этого метода понятно, что мы создаем пользователя
        // со всеми вытекающими, непосредственно при выполнении программы.
        // На этом настройка проекта, пока, завершена./
        auth.inMemoryAuthentication().withUser(userBuilder.username("Art").password("Art")
                .roles("Employee"));
        auth.inMemoryAuthentication().withUser(userBuilder.username("Ivan").password("Ivan")
                .roles("HR"));
        auth.inMemoryAuthentication().withUser(userBuilder.username("Egor").password("Egor")
                .roles("Sales"));
    }
}
