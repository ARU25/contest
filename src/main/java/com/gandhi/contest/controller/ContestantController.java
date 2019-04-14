package com.gandhi.contest.controller;


import com.gandhi.contest.model.Contestant;
import com.gandhi.contest.repository.ContestantRepository;
import com.gandhi.contest.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/contestant")
public class ContestantController {

  private static final Logger logger = LoggerFactory.getLogger(ContestantController.class);

  @Autowired
  private FileStorageService fileStorageService;
  @Autowired
  private ContestantRepository contestantRepository;

  @RequestMapping(method = RequestMethod.POST)
  void createContestant(@RequestParam("file")MultipartFile file,
                        @RequestParam("name") String name,
                        @RequestParam("phoneNo") String phoneNo,
                        @RequestParam("email") String email){
    String fileName = fileStorageService.storeFile(file);
    final Contestant contestant;
    try {
      contestant = new Contestant();
      contestant.setPhoneNo(phoneNo);
      contestant.setEmail(email);
      contestant.setName(name);
      contestant.setFileName(fileName);
      contestantRepository.saveAndFlush(contestant);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

      @RequestMapping(method = RequestMethod.GET)
    public List<Contestant> getDocument() {
        final List<Contestant> contestants = contestantRepository.findAll();
        return contestants;

    }


}
