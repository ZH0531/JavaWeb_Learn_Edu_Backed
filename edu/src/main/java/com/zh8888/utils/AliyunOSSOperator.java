package com.zh8888.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliyunOSSOperator {


    @Autowired
    private AliyunOSSProperties properties;
    String endpoint;
    String bucketName;
    String region;

    @PostConstruct
    public void init() {
        endpoint = properties.getEndpoint();
        bucketName = properties.getBucketName();
        region = properties.getRegion();
    }

    public String upload(byte[] content, String originalFilename) throws Exception {

        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 填写Object完整路径，例如202406/1.png。Object完整路径中不能包含Bucket名称。
        //获取当前系统日期的字符串,格式为 yyyy/MM
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        //生成一个新的不重复的文件名
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        } finally {
            ossClient.shutdown();
        }

        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }

    /**
     * 删除OSS上的文件
     *
     * @param fileUrl 文件的完整访问路径
     * @throws Exception 删除过程中可能抛出的异常
     */
    public void delete(String fileUrl) throws Exception {
        // 检查URL是否为空
        if (fileUrl == null || fileUrl.isEmpty()) {
            return;
        }

        // 从完整URL中提取objectName
        String objectName = extractObjectNameFromUrl(fileUrl);
        if (objectName == null) {
            System.out.println("无法从URL中提取对象名称: " + fileUrl);
            return;
        }

        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            // 删除文件
            ossClient.deleteObject(bucketName, objectName);
        } catch (OSSException oe) {
            System.out.println("捕获到OSS异常，请求已发送到OSS，但因某些原因被拒绝并返回错误响应。");
            System.out.println("错误信息:" + oe.getErrorMessage());
            System.out.println("错误代码:" + oe.getErrorCode());
            System.out.println("请求ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            throw oe; // 重新抛出异常供上层处理
        } catch (ClientException ce) {
            System.out.println("捕获到客户端异常，这意味着客户端在尝试与OSS通信时遇到严重内部问题，例如无法访问网络。");
            System.out.println("错误信息:" + ce.getMessage());
            throw ce; // 重新抛出异常供上层处理
        } finally {
            ossClient.shutdown();
        }
    }

    /**
     * 从完整的URL中提取OSS对象名称
     *
     * @param url 完整的文件访问URL
     * @return 对象名称，如果无法提取则返回null
     */
    private String extractObjectNameFromUrl(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }

        try {
            // 示例URL格式: https://bucket-name.endpoint/dir/filename
            // 需要提取: dir/filename
            String endpointHost = endpoint.replace("https://", "").replace("http://", "");
            int bucketAndEndpointLength = bucketName.length() + endpointHost.length() + 1; // +1 for the dot between bucket and endpoint
            int protocolEndIndex = url.indexOf("://") + 3;
            int objectNameStartIndex = protocolEndIndex + bucketAndEndpointLength + 1; // +1 for the slash

            if (objectNameStartIndex < url.length()) {
                return url.substring(objectNameStartIndex);
            }
        } catch (Exception e) {
            System.out.println("从URL提取对象名称时出错: " + e.getMessage());
        }

        return null;
    }
}