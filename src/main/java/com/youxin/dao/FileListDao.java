package com.youxin.dao;

import com.youxin.entities.FileList;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class FileListDao {

    static Integer initId = 1;

    HashMap fileListMap = new HashMap<String,FileList>();

    public void save(FileList fileList){
        fileList.setId(initId++);
        fileListMap.put(fileList.getId(),fileList);
    }

    public Collection<FileList> findAll(){
        return fileListMap.values();
    }

}
