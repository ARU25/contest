package com.gandhi.contest.controller;

import com.gandhi.contest.dto.ResultContainer;
import com.gandhi.contest.model.Contestant;
import com.gandhi.contest.model.Result;
import com.gandhi.contest.repository.ContestantRepository;
import com.gandhi.contest.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/result")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ContestantRepository contestantRepository;


    @RequestMapping(method = RequestMethod.GET)
    public List<Result> getResult() {
        final List<Result> results = resultRepository.findAll();
        return results;
    }

    @RequestMapping(method = RequestMethod.POST)
    void save(@RequestBody final ResultContainer resultContainer){
        final List<Result> resultList = resultContainer.getResultList();
        for (Result result:resultList){
                final Contestant contestant = contestantRepository.findById(result.getId()).get();
            final Result saveResult = new Result();
            saveResult.setRank(result.getRank());
            saveResult.setContestant(contestant);
            resultRepository.saveAndFlush(saveResult);
        }
    }
}
