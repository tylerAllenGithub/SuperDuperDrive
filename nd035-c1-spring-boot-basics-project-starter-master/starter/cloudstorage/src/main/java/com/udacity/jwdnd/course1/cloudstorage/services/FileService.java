package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
    FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean addFile(MultipartFile fileUpload, Integer userid){
        try {
            InputStream fis = fileUpload.getInputStream();
            String fileName = fileUpload.getOriginalFilename();
            String contentType = fileUpload.getContentType();
            String size = String.valueOf(fileUpload.getSize());
            if(Long.valueOf(size) <= 0)
                return false;
            Integer fileId=null;
            File file = new File(fileId, fileName, contentType, size, userid, fileUpload.getBytes());
            fileMapper.insert(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public File getFile(String fileName){
        return fileMapper.getFile(fileName);
    }
    public List<File> getFiles(Integer userId){
        return fileMapper.getFiles(userId);
    }
}
