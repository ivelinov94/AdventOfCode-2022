package com.wankish.day4;

import com.wankish.utils.FileReader;
import com.wankish.utils.Logger;

import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public  static void main(String[] args) throws URISyntaxException {
        FileReader reader = new FileReader();
        List<String> dataset = reader.readFile("day4.txt");
        int totalScore = 0;
        int total2Score = 0;
        for(String set: dataset) {
            String[] split = set.split(",");
            Section section1 = new Section(split[0]);
            Section section2 = new Section(split[1]);

            totalScore += checkContainedSections(section1, section2);
            total2Score += checkOverlappingSections(section1, section2);
        }

        Logger.log("Total sections contained: " + totalScore);
        Logger.log("Total sections overlapped: " + total2Score);

    }

    private static int checkOverlappingSections(Section section1, Section section2) {
        boolean isOverlapped = section1.isSectionOverlapped(section2);
        if(isOverlapped) {
            return 1;
        }

        boolean isSecondOverlapped = section2.isSectionOverlapped(section1);

        if(isSecondOverlapped) {
            return 1;
        }

        return 0;
    }

    private static int checkContainedSections(Section first, Section second) {
        boolean isSecondSectionContained = first.isSectionContained(second);
        boolean isFirstSectionContained = second.isSectionContained(first);

        if(isFirstSectionContained || isSecondSectionContained) {
            return 1;
        }

        return 0;
    }
}
