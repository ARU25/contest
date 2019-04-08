package com.gandhi.contest.controller;

import com.gandhi.contest.model.Document;
import com.gandhi.contest.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Document> getDocument() {
        final List<Document> documents = documentService.getAllDocuments();
        return documents;

    }


    @RequestMapping(value = "/{fileId}/download",method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        final Document document = documentService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "index.jpeg" + "\"")
                .body(new ByteArrayResource(document.getFile()));

    }

}
