package com.ruoyi.detect.controller;


import com.ruoyi.detect.service.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/python")
public class PythonController {
    @Autowired
    private PythonService pythonService;

    @RequestMapping("/face")
    public void face() throws IOException {
        pythonService.faceRecognition();
    }
}
