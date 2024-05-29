package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controlador {
	@GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/resultados")
    public String resultados(
            @RequestParam(name="type") String type,
            @RequestParam(name="start", required=false, defaultValue="0") Integer start,
            @RequestParam(name="end", required=false, defaultValue="0") Integer end,
            @RequestParam(name="table", required=false, defaultValue="0") Integer table,
            Model model) {
        if (type.equals("primos")) {
            List<Integer> primos = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                if (esPrimo(i)) {
                    primos.add(i);
                }
            }
            model.addAttribute("type", "primos");
            model.addAttribute("results", primos);
            
        } else if (type.equals("tabla")) {
            List<String> tablaMultiplicar = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                int result = table * i;
                tablaMultiplicar.add(table + " x " + i + " = " + result + " " + (result % 2 == 0 ? "[✓]" : "[ ]"));
            }
            model.addAttribute("type", "tabla");
            model.addAttribute("results", tablaMultiplicar);
        }
        return "resultados";
    }

    private boolean esPrimo(int num) {
        if (num <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

}
