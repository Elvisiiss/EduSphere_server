package com.lx.edusphere_server.entity;

public class Image_in_table {
    private Long id;
    private Long belong_user;
    private String file_name;
    private String file_url;
    private Integer is_deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBelong_user() {
        return belong_user;
    }

    public void setBelong_user(Long belong_user) {
        this.belong_user = belong_user;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }
}
