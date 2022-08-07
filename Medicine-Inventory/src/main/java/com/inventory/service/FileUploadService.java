package com.inventory.service;

import com.inventory.Util.FileConstant;
import com.inventory.batch.JobCompletionNotificationListener;
import com.inventory.businessException.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {

    private static final Logger log = LoggerFactory.getLogger(FileUploadService.class);

    public void uploadFile(MultipartFile file)  {
        log.info("File upload started...");
        try {
            file.transferTo(new File(FileConstant.FILE_UPLOAD_LOCATION+file.getOriginalFilename()));
        }catch (IOException e){
                log.error("File upload unsuccessful !"+ e);
                throw new FileUploadException("File upload unsuccessful !");
        }catch (Exception e){
            log.error("File upload unsuccessful !"+ e);
            throw new FileUploadException("File upload unsuccessful !");
        }
        log.info("File upload completed...");
    }

}
