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
 * Created by Administrator on 2017/8/16.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {


    @GetMapping("/createImage")
    public void createImage(@RequestParam("securityCode") String securityCode) throws IOException,FileNotFoundException{
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
