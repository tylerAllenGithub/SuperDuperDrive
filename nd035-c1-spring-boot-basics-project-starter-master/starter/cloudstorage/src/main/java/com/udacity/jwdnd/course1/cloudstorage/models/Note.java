package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {
    private int noteid;
    private String notetitle;
    private Integer userid;
    private String notedescription;

    public Note(){
    }

    public Note(Integer noteid, String notetitle,Integer userid, String notedescription){
        this.noteid = noteid;
        this.notetitle = notetitle;
        this.userid = userid;
        this.notedescription = notedescription;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }
}
