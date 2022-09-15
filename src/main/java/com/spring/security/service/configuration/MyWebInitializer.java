package com.spring.security.service.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
// Расширяем класс со страшным названием снова. Но в этот раз немного подробней о том, что
// мы тут имплиментируем.
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // В этот метод мы бы передавали класс конфигурации приложения, но только в том случае,
    // если это не web приложение. Нам оно не надо, вернем null
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }
    // А вот сюда мы передадим объект нашей конфигурации, как мы видим это массив, то есть мы
    // можем передать несколько объектов, но сейчас оно нам тоже не нужно.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};
    }
    // Ну здесь все просто, конфиг диспетчера один, значит и диспетчер один, сюда передаем
    // команду через которую к нему обращаемся, было бы несколько диспетчером, должны были бы
    // добавить несколько команд.
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    // Здесь все, теперь добавим web-сервер, не будем описывать как мы это делаем,
    // просто отметим что адрес у него будет "/app".

    // Здесь мы создали DispatcherServletInitializer, а теперь нам надо то же самое
    // сделать для Security компонента. В пакете configuration создадим MySecurityInitializer и
    // перейдем в него
}
