package com.util;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUpload {

    @Value("${reggie.path}")
    private String AbsPath;

    public String upload(MultipartFile file) {


        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!isImg(suffix)) {
            return null;
        }
        String newName = UUID.randomUUID().toString() + "." + suffix;
        File destFile = new File(AbsPath, newName);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return newName;
    }

    private boolean isImg(String suffix) {
        String regex = "(?i)(png|jpg|jpeg)$";
        return suffix.matches(regex);
    }


}
