package com.example.server.repository;

import com.example.server.entity.Attachment;
import com.example.server.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {

    AttachmentContent findByAttachment(Attachment attachment);

}
