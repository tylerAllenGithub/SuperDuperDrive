package com.udacity.jwdnd.course1.cloudstorage.mappers;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFile(String filename);

    @Select("SELECT filename FROM FILES WHERE userid = #{userid}")
    String getFileNames(Integer userid);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> getFiles(Integer userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insert(File file);
}
