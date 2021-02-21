package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS(url, username, filesize, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int insert(File file);
}
