package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.FileEntity;
import com.ljd.Food_Delivery.domain.mapper.FileMapper;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements FileService {
    @Override
    public boolean ImgUpload(MultipartFile file) {
        try {
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + file.getName();

            FileEntity fileEntity = FileEntity.builder()
                    .fileName(fileName + "img")
                    .fileLength(file.getSize())
                    .build();
            file.transferTo(
                    new File("/Users/Project/myself/Food_delivery/src/main/resources/file/"
                            + fileName + ".jpg"));
            return saveOrUpdate(fileEntity);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.FILE_UPLOAD_ERROR);
        }

    }

    @Override
    public boolean ImgDownLoad(HttpServletRequest request) {
        return false;
    }
}
