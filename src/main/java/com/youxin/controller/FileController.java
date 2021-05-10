package com.youxin.controller;



import com.youxin.entities.FileList;
import com.youxin.dao.FileListDao;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    private FileListDao fileListDao;


    @RequestMapping("/showFileList")
    public String showAllFileLists(Model model){
        Collection<FileList> fileLists = fileListDao.findAll();
        model.addAttribute("fileLists",fileLists);
        return "filemanager/filelist";
    }

    @GetMapping("/toUpload")
    public String toUpload(){
        return "filemanager/upload";
    }

    @PostMapping("/uploadFile")
    public String  uploadFile(MultipartFile file, HttpServletRequest req){
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf('.'));

        //先上传到服务器的file文件中
        String path = req.getServletContext().getRealPath("files");

        String uuid = UUID.randomUUID().toString();
        String filepath=path+"/"+uuid+suffix;
        System.out.println(filepath);
        FileList fileList = new FileList();
        fileList.setName(uuid+suffix);
        try {
            //将上传的文件复制到自定义路径中
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(filepath));
            //复制到F盘中的file文件夹中
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File("F:/file/"+uuid+suffix));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileList loadFile = new FileList(null, uuid+suffix);
        fileListDao.save(loadFile);
        return "redirect:/showFileList";
    }

    @GetMapping("/download/{id}/{name}")
    public void  downLoad(@PathVariable("id") Integer id, @PathVariable("name") String name, HttpServletRequest req, HttpServletResponse resp){
        //设置响应流中文件进行下载
        resp.setHeader("Content-Disposition","attachment;filename="+name);
        //把二进制流放入到响应体中.
        ServletOutputStream os  = null;
        try {
            os = resp.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String  path  =req.getServletContext().getRealPath("files");
        File file  =  new File(path,name);
        byte[]  bytes  = new byte[0];
        try {
            bytes = FileUtils.readFileToByteArray(file);
            os.write(bytes);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
