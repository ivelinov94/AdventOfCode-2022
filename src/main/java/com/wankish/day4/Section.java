package com.wankish.day4;

public class Section {
    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    private final int start;
    private final int end;

    public Section(String range) {
        String[] split = range.split("-");
        start = Integer.parseInt(split[0]);
        end = Integer.parseInt(split[1]);
    }

    public boolean isSectionContained(Section oppositeSection) {
        boolean isCurrentStartSmallerOrEqual = start <= oppositeSection.getStart();
        boolean isCurrentEndBiggerOrEqual = end >= oppositeSection.getEnd();

        return isCurrentStartSmallerOrEqual && isCurrentEndBiggerOrEqual;
    }

    public boolean isSectionOverlapped(Section oppositeSection) {
        if(start == oppositeSection.getStart()) {
            return true;
        }

        if(end == oppositeSection.getEnd()) {
            return true;
        }

        for(int i = start; i <= end; i++) {
            for(int j = oppositeSection.getStart(); j <= oppositeSection.getEnd(); j++) {
                if(i == j) {
                    return true;
                }
            }
        }

        return false;

    }
}
