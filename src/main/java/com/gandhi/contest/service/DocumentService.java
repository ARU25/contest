package com.gandhi.contest.service;

import com.gandhi.contest.model.Document;
import com.gandhi.contest.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    public Document getFile(String fileId){
        return  documentRepository.getOne(UUID.fromString(fileId));
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
}
