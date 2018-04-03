package com.backend.admin.service;

import org.apache.log4j.lf5.util.StreamUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author caojiantao
 */
@Service
public class UploadService {

    @Value("${upload_path}")
    private String uploadPath;

    public String uploadFile(MultipartFile file) throws IOException {
        return uploadFile(file.getInputStream(), file.getOriginalFilename());
    }

    public String uploadFile(InputStream is, String fileName) {
        File directory = new File(uploadPath);
        File targetFile = new File(directory, getTargetFileName(fileName));
        String url = "";
        try {
            OutputStream os = new FileOutputStream(targetFile);
            StreamUtils.copy(is, os);
            url = "/files/" + targetFile.getName();
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 得到存储文件名
     * @param originFileName 源文件名
     * @return 文件地址
     */
    private String getTargetFileName(String originFileName) {
        String[] tempArr = originFileName.split("\\.");
        return tempArr[0] + "_" + System.currentTimeMillis() + "." + (tempArr.length > 1 ? tempArr[1] : "");
    }
}
