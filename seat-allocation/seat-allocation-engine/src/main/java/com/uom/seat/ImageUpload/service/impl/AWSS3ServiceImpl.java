package com.uom.seat.ImageUpload.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.uom.seat.ImageUpload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
public class AWSS3ServiceImpl implements FileService {
    @Autowired
    private AmazonS3Client awsS3Client;

    @Override
    public String uploadFile(MultipartFile file) {
        String extension= StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID().toString()+"."+extension;

        ObjectMetadata metaData=new ObjectMetadata();
        metaData.setContentLength(file.getSize());
        metaData.setContentType(file.getContentType());

        try{
            awsS3Client.putObject("alloc-at",key,file.getInputStream(),metaData);
        }
        catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error occured  while uploading file");

        }

        awsS3Client.setObjectAcl("alloc-at",key, CannedAccessControlList.PublicRead);
        return awsS3Client.getResourceUrl("alloc-at",key);
    }
}
