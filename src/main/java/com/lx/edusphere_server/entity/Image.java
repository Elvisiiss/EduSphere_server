package com.lx.edusphere_server.entity;

import java.io.File;

public class Image {
    private String file_name;
    private String file_url;
    public Image() {}
    public Image(String file_url, String file_name) {
        this.file_name = file_name;
        this.file_url = file_url;
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
}
