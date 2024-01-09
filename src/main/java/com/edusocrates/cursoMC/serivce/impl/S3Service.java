package com.edusocrates.cursoMC.serivce.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class S3Service {


    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String s3Bucket;

    public void uploadFile(String localFilePath){
        try{
        LOG.info("INICIANDO UPLOAD DE ARQUIVOS!");
        File file = new File(localFilePath);
        s3Client.putObject(new PutObjectRequest(s3Bucket,"teste",file));
        LOG.info("ARQUIVOS INSERIDOS COM SUCESSO!");
        }catch (AmazonServiceException e){
            LOG.info("ERRO AO INSERIR ARQUIVO!");
            LOG.info("AmazonServiceException "+ e.getErrorMessage());
            LOG.info("Status code:  "+ e.getStatusCode());

        }catch (AmazonClientException e){
            LOG.info("ERRO AO INSERIR ARQUIVO!");
            LOG.info("AmazonClientException: "+ e.getMessage());

        }


    }


}
