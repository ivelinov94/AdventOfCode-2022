package com.wankish.day1;

import com.wankish.utils.FileReader;
import com.wankish.utils.Logger;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    private static ArrayList<Integer> cacheScanResults;
    public static void main(String[] args) throws URISyntaxException {
        int max = getMaxResult();
        Logger.log(max);
        int max3Results = getTop3ResultsSum();
        Logger.log(max3Results);
    }

    private static ArrayList<Integer> readFormatAndSortResults() throws URISyntaxException {
        if(cacheScanResults != null) {
            return cacheScanResults;
        }
        List<String> scanResult = getScanResult();
        ArrayList<Integer> results = new ArrayList<>();
        int currentIterationResult = 0;
        for(String res : scanResult) {
            if (Objects.equals(res, "")) {
                results.add(currentIterationResult);
                currentIterationResult = 0;
                continue;
            }
            currentIterationResult += Integer.parseInt(res);
        }
        results.sort((o1, o2) -> o2 - o1);
        cacheScanResults = results;
        return results;
    }

    private static List<String> getScanResult() throws URISyntaxException {
        FileReader reader = new FileReader();
        return reader.readFile("day1.txt");
    }

    private static Integer getTop3ResultsSum() throws URISyntaxException {
        ArrayList<Integer> results = readFormatAndSortResults();
        return results.get(0) + results.get(1) + results.get(2);
    }

    private static Integer getMaxResult() throws URISyntaxException {
        ArrayList<Integer> results = readFormatAndSortResults();
        return results.get(0);
    }
}
