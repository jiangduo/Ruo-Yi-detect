package com.ruoyi.detect.controller;


import com.ruoyi.detect.service.AlgorithmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/test")
public class AlgorithmController {

    @Autowired
    private AlgorithmService algorithmService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/add")
    public String add(@RequestParam("num1") int num1, @RequestParam("num2") int num2, Model model) throws IOException {
        String result = algorithmService.runPythonAlgorithm(num1, num2);
        model.addAttribute("result", result);
        return "index";
    }
}
