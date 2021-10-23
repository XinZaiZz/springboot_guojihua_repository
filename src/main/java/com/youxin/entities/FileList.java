package com.youxin.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("文件上传类")
public class FileList {
    @ApiModelProperty("文件id")
    private Integer id;

    @ApiModelProperty("文件名")
    private String name;

    @Override
    public String toString() {
        return "FileList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileList() {
    }

    public FileList(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
