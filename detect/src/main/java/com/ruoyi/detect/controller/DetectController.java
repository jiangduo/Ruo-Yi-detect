package com.ruoyi.detect.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/direct")
public class DetectController {

    @GetMapping("/get")
    public String get(){
        return "this is string";
    }



    @PostMapping("/graph")
    public Map<String, String> generateGraph(@RequestBody(required = false) Map<String, Object> payload) throws IOException, InterruptedException {
        // Convert payload to JSON string
        String jsonPayload = new ObjectMapper().writeValueAsString(payload);
        System.out.println("jinlaie!!");
        // Execute Python script
        Process process = Runtime.getRuntime().exec(new String[] { "python", "graph.py", jsonPayload });
        System.out.println(process.getOutputStream().toString());
        process.waitFor();

        // Read Python output
        InputStream inputStream = process.getInputStream();
        String output = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        inputStream.close();

        // Return result
        Map<String, String> result = new HashMap<>();
        result.put("message", "Graph generated successfully");
        result.put("image", "data:image/png;base64," + output);
        return result;
    }




    @Autowired
    private ResourceLoader resourceLoader;
    //vue中使用axios发起get请求，到springboot的REstApi，并将响应结果存储在vue组件的data属性，
    // @GetMapping("/detect/image")
    // public ResponseEntity<byte[]> getImage() throws IOException {
    //     //调度python算法
    //     Process process = Runtime.getRuntime().exec("/Users/jiang/PycharmProjects/pythonProject/HelloWorld/main.py");
    //     System.out.println(process);
    //     //读取python输出
    //     // List<String> output = IOUtils.readLines(process.getInputStream());
    //     //将图片转换为java bufferedImage对象
    //     BufferedImage image = ImageIO.read(process.getInputStream());
    //
    //     //使用ByteArrayOutputStream类将图像转换为字节数组。
    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //     ImageIO.write(image,"png",outputStream);
    //
    //
    //     //将图片读取为字节数组，
    //     byte[] imagesBytes = outputStream.toByteArray();
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.IMAGE_PNG);
    //     return new ResponseEntity<>(imagesBytes,headers, HttpStatus.OK);
    // }

}
