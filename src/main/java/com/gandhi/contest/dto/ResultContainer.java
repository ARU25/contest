package com.gandhi.contest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gandhi.contest.model.Result;

import java.util.List;

public class ResultContainer {
    private final List<Result> resultList;

    public List<Result> getResultList() {
        return resultList;
    }

    @JsonCreator
    public ResultContainer(@JsonProperty("resultList") List<Result> resultList) {
        this.resultList = resultList;
    }
}
