package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/calculator")
    public String calculator() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("num1") double num1,
                            @RequestParam("num2") double num2,
                            @RequestParam("operation") String operation,
                            Model model) {
        double result = 0;

        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("error", "Деление на ноль невозможно");
                    return "result";
                }
                break;
        }

        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/converter")
    public String converter() {
        return "converter";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam("fromCurrency") String fromCurrency,
                          @RequestParam("toCurrency") String toCurrency,
                          @RequestParam("amount") double amount,
                          Model model) {

        double rate = 1.0;

        if (!fromCurrency.equals(toCurrency)) {
            if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
                rate = 0.85;
            } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
                rate = 1.18;
            } else if (fromCurrency.equals("USD") && toCurrency.equals("RUB")) {
                rate = 75.0;
            } else if (fromCurrency.equals("RUB") && toCurrency.equals("USD")) {
                rate = 0.013;
            }
        }

        double result = amount * rate;

        model.addAttribute("fromCurrency", fromCurrency);
        model.addAttribute("toCurrency", toCurrency);
        model.addAttribute("amount", amount);
        model.addAttribute("result", result);
        model.addAttribute("rate", rate);

        return "conversionResult";
    }
}

