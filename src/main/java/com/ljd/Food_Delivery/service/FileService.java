package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService extends IService<FileEntity> {

    String ImgUpload(MultipartFile file);

    void ImgDownLoad(String name,HttpServletResponse response);
}
