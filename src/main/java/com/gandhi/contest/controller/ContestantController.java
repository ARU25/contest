package com.gandhi.contest.controller;


import com.gandhi.contest.model.Contestant;
import com.gandhi.contest.model.Document;
import com.gandhi.contest.repository.ContestantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/contestant")
public class ContestantController {

  @Autowired
  ContestantRepository contestantRepository;

  @RequestMapping(method = RequestMethod.POST)
  void createContestant(@RequestParam("file")MultipartFile file,
                        @RequestParam("name") String name,
                        @RequestParam("phoneNo") String phoneNo,
                        @RequestParam("email") String email){

    final Document document;
    final Contestant contestant;
    try {
      document = new Document();
      contestant = new Contestant();
      contestant.setPhoneNo(phoneNo);
      contestant.setEmail(email);
      contestant.setName(name);
      contestant.setDocument(document);
      contestantRepository.saveAndFlush(contestant);
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

}
