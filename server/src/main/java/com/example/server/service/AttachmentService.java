package com.example.server.service;

import com.example.server.entity.Attachment;
import com.example.server.entity.AttachmentContent;
import com.example.server.payload.ApiResponse;
import com.example.server.repository.AttachmentContentRepository;
import com.example.server.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;


    @Autowired
    AttachmentContentRepository attachmentContentRepository;


    public ApiResponse upload(MultipartHttpServletRequest request) throws IOException {
        List<UUID> photoIds=new ArrayList<>();
        Iterator<String> fileNames = request.getFileNames();
        List<MultipartFile> files = request.getFiles(fileNames.next());
        for (MultipartFile file : files) {

            Attachment attachment=new Attachment();
            attachment.setOriginalName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            attachment.setFileName(file.getName());
          attachment=  attachmentRepository.save(attachment);
            AttachmentContent content =new AttachmentContent();
            content.setAttachment(attachment);
            content.setBytes(file.getBytes());
            attachmentContentRepository.save(content);

            photoIds.add(attachment.getId());

        }

return new ApiResponse("OK",true,photoIds);


    }

    public HttpEntity<?> download(UUID id) {
        Attachment byId = attachmentRepository.getById(id);
        AttachmentContent content = attachmentContentRepository.findByAttachment(byId);
return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(byId.getContentType()))
        .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;fileName=\""+byId.getFileName()+"\"")
.body(content.getBytes());
    }
}
