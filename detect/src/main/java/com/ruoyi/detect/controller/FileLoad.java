package com.ruoyi.detect.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/detect/file")
public class FileLoad {

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("files")MultipartFile[] files){
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();

        }
    }
}
