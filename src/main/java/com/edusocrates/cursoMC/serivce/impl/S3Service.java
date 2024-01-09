package com.edusocrates.cursoMC.serivce.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;


@Service
public class S3Service {


    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String s3Bucket;

    public URI uploadFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        InputStream is = multipartFile.getInputStream();//objeto de leitura que encapsula um objeto de leitura pela origem
        String contentType = multipartFile.getContentType();//tipo do arquivo
        return uploadFile(is, fileName,  contentType);

    }

    public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            s3Client.putObject(new PutObjectRequest(s3Bucket, fileName, inputStream, meta));

            return s3Client.getUrl(s3Bucket, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException("erro ao converter a URL para URI");
        }
    }


}
