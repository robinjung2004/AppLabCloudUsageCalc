package com.example;

import java.util.List;

public class Result {
    private List<ResultItem> result;

    public Result(List<ResultItem> result) {
        this.result = result;
    }

    public List<ResultItem> getResult() {
        return result;
    }

    public void setResult(List<ResultItem> result) {
        this.result = result;
    }
}
