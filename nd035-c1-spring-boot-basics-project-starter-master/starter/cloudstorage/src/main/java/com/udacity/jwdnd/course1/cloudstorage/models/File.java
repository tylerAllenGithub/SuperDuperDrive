package com.udacity.jwdnd.course1.cloudstorage.models;

public class File {
    private Integer fileid;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;

    private byte[] filedata;

    public File(Integer fileid, String filename, String contenttype, String filesize, Integer userid, byte[] filedata){
        this.fileid= fileid;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFileName() {
        return filename;
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFileSize() {
        return filesize;
    }

    public void setFileSize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }
}
