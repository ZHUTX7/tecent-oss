package com.ztx.oss;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/oss")
public class OSSController {

    @Resource
    OSSService ossService;

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest req)  {
        ossService.upload(file);
        return "ok";
    }
}
