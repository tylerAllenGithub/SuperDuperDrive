package com.udacity.jwdnd.course1.cloudstorage.mappers;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE notetitle = #{notetitle}")
    Note getNote(String notetitle);

    @Insert("INSERT INTO NOTES (notetitle, description) VALUES(#{notetitle}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);
}
