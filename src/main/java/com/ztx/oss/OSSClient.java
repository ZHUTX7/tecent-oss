package com.ztx.oss;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Component;

@Component
public class OSSClient {

    private COSClient cosClient;

    public COSClient getCosClient(){
        if(cosClient == null){
            initialOSS();
            //initialOSS2();
        }
        return cosClient;
    }

    private void initialOSS(){
        // 1 初始化用户身份信息（secretId, secretKey）。
        // SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = "AKIDGJ6cleTcomVzC9lKXTaQ0JK7Udiz4qPh";
        String secretKey = "ua2DvPdaI5brqX7RWJJ6dvxLVP1PYR5S";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-beijing");
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        this.cosClient = new COSClient(cred, clientConfig);

    }

    // 临时密钥获取OSS
    private void initialOSS2(){
        // 1 传入获取到的临时密钥 (tmpSecretId, tmpSecretKey, sessionToken)
        String tmpSecretId = "SECRETID";
        String tmpSecretKey = "SECRETKEY";
        String sessionToken = "TOKEN";
        BasicSessionCredentials cred = new BasicSessionCredentials(tmpSecretId, tmpSecretKey, sessionToken);
       // 2 设置 bucket 的地域, COS 地域的简称请参阅 https://cloud.tencent.com/document/product/436/6224
       // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参阅源码或者常见问题 Java SDK 部分
        Region region = new Region("COS_REGION");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端
        this.cosClient = new COSClient(cred, clientConfig);
    }
}
