package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.FileEntity;
import com.ljd.Food_Delivery.domain.mapper.FileMapper;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements FileService {

    @Value("${Food_delivery.path}/")
    private String Food_delivery;

    @Override
    public boolean ImgUpload(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString();

            FileEntity fileEntity = FileEntity.builder()
                    .fileName(fileName)
                    .fileLength(file.getSize())
                    .build();
            file.transferTo(
                    new File(Food_delivery + fileName + ".jpg"));
            return saveOrUpdate(fileEntity);
        } catch (Exception e) {
            throw new FoodException(ErrorCode.FILE_UPLOAD_ERROR);
        }

    }

    @Override
    public void ImgDownLoad(String name, HttpServletResponse response) {
        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(Food_delivery + name));

            //输出流，通过输出流将文件写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
