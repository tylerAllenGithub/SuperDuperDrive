package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credential {
    private Integer credentialid;
    private String url;
    private String username;
    private Integer userid;
    private String key;
    private String password;

    public Credential()
    {
        //empty
    }
    public Credential(Integer credentialid, String url, String username, String key, Integer userid, String password)
    {
        this.credentialid = credentialid;
        this.url = url;
        this.username = username;
        this.userid = userid;
        this.key = key;
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public Integer getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(Integer credentialid) {
        this.credentialid = credentialid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
