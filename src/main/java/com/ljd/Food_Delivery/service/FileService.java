package com.ljd.Food_Delivery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljd.Food_Delivery.domain.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileService extends IService<FileEntity> {

    boolean ImgUpload(MultipartFile file);

    boolean ImgDownLoad(HttpServletRequest request);
}
