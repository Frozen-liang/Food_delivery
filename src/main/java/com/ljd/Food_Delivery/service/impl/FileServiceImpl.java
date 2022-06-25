package com.ljd.Food_Delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljd.Food_Delivery.domain.entity.FileEntity;
import com.ljd.Food_Delivery.domain.mapper.FileMapper;
import com.ljd.Food_Delivery.exception.ErrorCode;
import com.ljd.Food_Delivery.exception.FoodException;
import com.ljd.Food_Delivery.service.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
    public String ImgUpload(MultipartFile file) {
        try {
            // 得到原始的图片名字
            String originalFilename = file.getOriginalFilename();
            // 对原始名字进行切割 把文件类型切割出来
            String substring = originalFilename.substring(originalFilename.lastIndexOf('.'));

            String fileName = UUID.randomUUID().toString()+substring;

            FileEntity fileEntity = FileEntity.builder()
                    .fileName(fileName)
                    .fileLength(file.getSize())
                    .build();
            file.transferTo(
                    new File(Food_delivery + fileName));
            saveOrUpdate(fileEntity);
            return fileName;
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

            IOUtils.copy(fileInputStream,outputStream);

            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
