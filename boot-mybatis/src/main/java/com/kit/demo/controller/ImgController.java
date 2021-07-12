package com.kit.demo.controller;

import com.kit.demo.util.ValidateCode;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.*;

/**
 * @author Kit
 * @date 20210712
 * 图片相关操作
 */
@RestController
@RequestMapping(value = "/img")
public class ImgController {


    @GetMapping("/createImage")
    public void createImage(@RequestParam("securityCode") String securityCode) {
        BufferedImage bufferedImage = ValidateCode.getImage(securityCode);
        try{
            File f = new File("F:\\data\\MyFile.png");
            ImageIO.write(bufferedImage, "PNG", f);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
