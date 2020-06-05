package com.winston.practice.controllers;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/file")
@Slf4j
public class FileTestController {

    /**
     * 启动的时候  设置 JVM参数 为 -Xms500m -Xmx500m  来测试此场景 Docker.dmg 文件是Docker的安装包 一共600多M
     * FileUtils.readFileToByteArray  把文件内容一次加载到内存中，会导致内存溢出
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> downFile(HttpServletResponse response) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "Docker.dmg");
        //URL url = this.getClass().getClassLoader().getResource("Docker.dmg");
        File downloadFile = new File("/Users/wchen/Downloads/Docker.dmg");
        return new ResponseEntity<>(FileUtils.readFileToByteArray(downloadFile), headers, HttpStatus.CREATED);
    }

    /**
     * 分批读取文件内容  每次 读取1024kb 避免一次性占满内存
     * @param response
     * @throws IOException
     */
    @GetMapping("/download-buffer")
    public void downFileByBuffer(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=Docker.dmg");
        try (
          //InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream("Docker.dmg");
          InputStream fileInputStream = new FileInputStream("/Users/wchen/Downloads/Docker.dmg")
        ) {
            byte[] buffer = new byte[1024]; //1024 是测试使用   实际环境需要设置更大
            int len = 0;
            int i = 0;
            while ((len = fileInputStream.read(buffer)) != -1) {
                log.info("第:{}次传输:{} 个字节",++i,len);
                if(len<1024) {
                    log.info("最后一次休息20秒，请到浏览器看效果，此时下载卡住了");
                    Thread.sleep(20*1000);
                    log.info("最后一次休息20秒，完成");
                }
                outputStream.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error(">>>处理失败", e);
        }
    }


}
