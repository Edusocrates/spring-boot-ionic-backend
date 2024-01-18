package com.edusocrates.cursoMC.serivce.impl;

import com.edusocrates.cursoMC.Utils.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile){
        String extensao = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
        if(!"png".equals(extensao) && !"jpg".equals(extensao)){
            throw new FileException("Somente imagem PNG ou JPG são aceitas!");
        }
        try {
            BufferedImage imagem = ImageIO.read(uploadedFile.getInputStream());
            if("png".equals(extensao)){
                imagem = pngToJpg(imagem);
            }
            return imagem;
        } catch (IOException e) {
            throw new FileException("Erro ao tentar ler arquivo!");
        }
    }

    public BufferedImage pngToJpg(BufferedImage imagem) {
        BufferedImage imagemJpg = new BufferedImage(imagem.getWidth(),imagem.getHeight(),BufferedImage.TYPE_INT_RGB);
        imagemJpg.createGraphics().drawImage(imagem,0,0, Color.WHITE,null);
        return imagemJpg;
    }

    public InputStream getInputStream(BufferedImage bufferedImage ,String extesao){
        try{
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,extesao,os);
            return new ByteArrayInputStream(os.toByteArray());
        }catch (IOException e){
            throw new FileException("Erro ao transformar arquivo");
        }
    }

    public BufferedImage cropSquare(BufferedImage imagem){
        int min = (imagem.getHeight() <= imagem.getWidth()) ? imagem.getHeight() : imagem.getWidth();
        return Scalr.crop(
                imagem,
                (imagem.getWidth()/2) - (min/2),
                (imagem.getHeight()/2)-(min/2),
                min,
                min
        );
    }
    public BufferedImage resize(BufferedImage imagem, int size){
        return Scalr.resize(imagem,Scalr.Method.ULTRA_QUALITY,size);
    }



}
