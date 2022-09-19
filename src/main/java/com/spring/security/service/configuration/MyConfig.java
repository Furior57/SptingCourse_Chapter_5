package com.spring.security.service.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

// Здесь ничего нового, указываем, что это конфигурация, указываем пакет для поиска компонентов,
// включаем поддержку MVC
@Configuration
@ComponentScan(basePackages = "com.spring.security")
@EnableWebMvc
public class MyConfig {
    // Ранее в xml файле мы прописывали префикс и суффикс для view файлов, мы особенно не углублялись
    // в то, как это работает, попробуем сейчас восполнить пробелы в знаниях. Для начала пробежимся по
    // цепочке работы MVC приложения. Какой-то запрос приходит на диспетчер сервлетов, он согласно
    // своей логике подбирает контроллер, который должен обработать этот запрос. Далее диспетчер
    // посылает запрос к этому контроллеру, тот обрабатывает его и посылает обратно ModelAndView,
    // требуемые данные и представление(отображение) данных. Диспетчер на основании этого ответа
    // ищет представление которое нужно использовать, с помощью ViewResolver, ответом он получает
    // имя view, в это view уже передаются данные. ViewResolver - это интерфейс имплементации
    // которого способны находить представления по View Name, состояние представления при этом
    // изменяться не будет, поэтому эти данные можно кешировать. Реализацией ViewResolver по
    // умолчанию является InternalResourceViewResolver, мы уже видели это название в xml конфигурации
    // диспетчера сервлетов. Эта реализация будет искать файлы к названию которых добавляются
    // суффикс и префикс. Префикс - /WEB-INF/views/, суффикс - .jsp. Почему класс помечен как internal?
    // Дело в том, что доступа извне к представлениям лежащим к папке /WEB-INF/view нет, все что
    // лежит в этой папке можно получить только с помощью контроллера, поэтому запрос через
    // адресную строку невозможен.
    // Реализация простейшая, создаем объект класса InternalResourceViewResolver, задаем префикс и
    // суффикс, возвращаем этот объект. Далее нам необходимо определить класс для настройки
    // DispatcherServlet, создадим этот класс в этом же пакете, назовем MyWebInitializer, перейдем
    // в него.
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver =
                new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC");
            dataSource.setUser("bestuser");
            dataSource.setPassword("bestuser");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
