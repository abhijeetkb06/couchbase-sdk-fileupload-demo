package com.couchbasesdk.fileupload.demo.service;

import com.couchbasesdk.fileupload.demo.model.LoadFile;
import com.couchbasesdk.fileupload.demo.repository.FileRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private static final String docIdConst = "PDF";

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String addFile(MultipartFile upload) throws IOException {

        String id = "doc:"+upload.getOriginalFilename();
        LoadFile loadFile = new LoadFile();
        loadFile.setId(id);
        loadFile.setFilename(upload.getOriginalFilename());
        loadFile.setFile(upload.getBytes());
        loadFile.setFileType(upload.getContentType());
        fileRepository.save(loadFile);
        System.out.println("****************File Loaded successfully **************"+loadFile.getFilename());
        //return as a string
        return id;
    }

    public LoadFile downloadFile(String id) throws IOException {

        //convert uri to byteArray
        //save data to LoadFile class

   /*     if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setFilename( gridFSFile.getFilename() );

            loadFile.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );

            loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );

            loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }*/


        LoadFile result = fileRepository.findById(id).orElse(null);

        return result;
    }

}
