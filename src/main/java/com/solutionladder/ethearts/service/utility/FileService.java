package com.solutionladder.ethearts.service.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.solutionladder.ethearts.persistence.entity.Resource;

/**
 * Utility class to help creation of resource - basically file.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Service
public class FileService {

    /**
     * 
     * @param file 
     * @param fileName Full path to the file
     * @return
     */
    public static Resource getResource(MultipartFile file, String fileName) {
        Resource resource = new Resource();
        resource.setBlob(true);
        resource.setDateCreated(new Date());
        resource.setExtension(getExtension(fileName));
        resource.setPath(fileName);
        resource.setSize(file.getSize());
        resource.setType(file.getContentType());
        
        return resource;
    }
    
    /**
     * Covert the MultipartFile to File 
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }
    
    /**
     * Get extension from the given file.
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }
}
