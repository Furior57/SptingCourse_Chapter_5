package com.spring.security.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Помечаем класс как контроллер
@Controller
public class MyController {
    // Определим первый метод, он будет выводить данные обо всех работниках
    @GetMapping("/")
    public String getInfoForAllEmps() {
        // А теперь создадим сам view, перейдем к нему
        return "view_for_all_employees";
    }

    @GetMapping("/hr_info")
    public String getInfoOnlyForHR() {

        return "view_for_hr";
    }

    @GetMapping("/manager_info")
    public String getInfoOnlyForManagers() {

        return "view_for_managers";
    }
}
