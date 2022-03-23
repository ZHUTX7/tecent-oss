package com.ztx.oss;

import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Service
public class OSSService {

    @Resource
    private OSSClient ossClient;

    public void upload(File file){

        //指定文件将要存放的存储桶 TODO：需要写一个静态类或者CONFIG管理桶NAME
        String bucketName = "ztx-1259017660";
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = "test";
        String fileName = "test"+System.currentTimeMillis();

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        PutObjectResult putObjectResult = ossClient.getCosClient().putObject(putObjectRequest);
        System.out.println("上传成功，时间"+putObjectResult.getDateStr());
    }
    public void upload(MultipartFile multipartFile){

        //指定文件将要存放的存储桶 TODO：需要写一个静态类或者CONFIG管理桶NAME
        String bucketName = "ztx-1259017660";
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = "test";
        String fileName = "test"+System.currentTimeMillis();
        File file = null;
        try {
            file = File.createTempFile("temp",null);
            multipartFile.transferTo(file);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file);
            PutObjectResult putObjectResult = ossClient.getCosClient().putObject(putObjectRequest);
            System.out.println("上传成功，时间"+putObjectResult.getDateStr());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            ossClient.getCosClient().shutdown();
        }

    }
}
