package com.ruoyi.detect.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Service
public class PythonService {
    public void faceRecognition() throws IOException {
        // 前面一半是本地环境下的python的启动文件地址，后面一半是要执行的python脚本地址
        String[] arguments = new String[]{"/Users/jiang/miniconda3/bin/python.exe", "/Users/jiang/Profession/IdeaProject/RuoYi-Vue/detect/src/main/resources/simple.py"};
        Process proc;
        try {
            proc = Runtime.getRuntime().exec(arguments);// 执行py文件
            // 用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            // InputStreamReader() ;InputStreamReader
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            // waitFor是用来显示脚本是否运行成功，1表示失败，0表示成功，还有其他的表示其他错误
            int re = proc.waitFor();
            System.out.println(re);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
