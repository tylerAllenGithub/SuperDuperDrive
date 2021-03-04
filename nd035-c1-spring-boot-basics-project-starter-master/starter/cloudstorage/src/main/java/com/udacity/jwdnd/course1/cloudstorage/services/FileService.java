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
            List<File> files = getFiles(userid);
            for(int i =0; i<files.size();i++)
            {
                if(files.get(i)!=null) {
                    if (files.get(i).getFileName().equals(fileName)) {
                        return false;
                    }
                }
            }
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
    public void deleteFile(Integer fileId){
        fileMapper.deleteFile(fileId);
    }
    public File getFileByID(Integer fileId){
        return fileMapper.getFileByID(fileId);
    }
    public byte[] getFileData(String fileName){ return fileMapper.getFileData(fileName);}

    public List<File> getFiles(Integer userId){
        return fileMapper.getFiles(userId);
    }
}
