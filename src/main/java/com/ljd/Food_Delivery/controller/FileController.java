package com.ljd.Food_Delivery.controller;

import com.ljd.Food_Delivery.dto.Response;
import com.ljd.Food_Delivery.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public Response<Boolean> fileUpload(MultipartFile file){
        return Response.ok(fileService.ImgUpload(file));
    }

    @GetMapping("/download")
    public Response<Boolean> fileDownload(HttpServletRequest request){
        return Response.ok(fileService.ImgDownLoad(request));
    }
}
