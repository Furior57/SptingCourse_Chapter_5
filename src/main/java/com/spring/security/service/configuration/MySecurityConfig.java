package com.spring.security.service.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
                .roles("EMPLOYEE"));
        auth.inMemoryAuthentication().withUser(userBuilder.username("Ivan").password("Ivan")
                .roles("HR"));
        auth.inMemoryAuthentication().withUser(userBuilder.username("Egor").password("Egor")
                .roles("MANAGER"));
    }
    // Имплементируем метод configure() который принимает HttpSecurity, этот объект
    // занимается тем, что дает разрешения перейти по url в зависимости от роли пользователя
    // котроый запрашивает этот переход. Перейдем ниже к реализации, чтобы увидеть
    // как это работает
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Такой записью мы указываем, что к стартовому view у нас имеют доступ такие то роли.
        // Можем не заполнять это поле и тогда любой пользователь будет иметь доступ
        // к этой странице, далее мы можем продолжить настройку доступа не начиная новое
        // выражение, прописываем новый адрес с помощью antMatchers() и методом hasRole()
        // устанавливаем туда доступ для HR, тут уже скобки пустыми оставить не получится.
        // В конце указываем такую конструкцию and().formLogin().permitAll(), она означает,
        // что форма логина-пароля будет запрашиваться у всех посетителей.
        http.authorizeHttpRequests().antMatchers("/").hasAnyRole("EMPLOYEE",
                "HR", "SALES", "MANAGER").antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info").hasRole("MANAGER")
                .and().formLogin().permitAll();
        // Однако то как мы сейчас реализовали наше представление не есть хорошо, зачем
        // ограничивать доступ к кнопке по роли, но оставлять ее видимой для всех.
        // Мы вполне себе можем скрыть эту кнопку для всех ролей кроме целевой.
        // Перейдем во view_for_all_employees на 10 строку и посмотрим как мы это реализуем.
    }
}
