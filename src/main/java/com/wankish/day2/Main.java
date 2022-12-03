package com.wankish.day2;

import com.wankish.utils.FileReader;
import com.wankish.utils.Logger;

import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        FileReader reader = new FileReader();
        List<String> dataset = reader.readFile("day2.txt");
        int totalScore = 0;
        int total2Score = 0;
        for(String set: dataset) {
            char opponent = set.charAt(0);
            char me = set.charAt(2);
            totalScore += getScore(opponent, me);
            total2Score += getSecondScore(opponent, me);
        }

        Logger.log("Total score: " + totalScore);
        Logger.log("Total Second score: " + total2Score);
    }

    private static Types getOppositeType(Types opponent, char me) {
        if(opponent == Types.PAPER) {
            if(me == 'X') {
                return Types.ROCK;
            } else if(me == 'Y') {
                return Types.PAPER;
            }

            return  Types.SCISSORS;
        } else if(opponent == Types.ROCK) {
            if(me == 'X') {
                return Types.SCISSORS;
            } else if(me == 'Y') {
                return Types.ROCK;
            }

            return  Types.PAPER;
        }

        if(me == 'X') {
            return Types.PAPER;
        } else if(me == 'Y') {
            return Types.SCISSORS;
        }

        return  Types.ROCK;
    }

    private static Types determineType(char res) throws UnknownTypeException {
        if(res == 'X' || res == 'A') {
            return Types.ROCK;
        }

        if(res == 'Y' || res == 'B') {
            return Types.PAPER;
        }

        if(res == 'Z' || res == 'C') {
            return Types.SCISSORS;
        }

        throw new UnknownTypeException(res);
    }

    private static ActionState determineActionState(Types opponent, Types me) {
        if(opponent == Types.PAPER) {
            if(me == Types.PAPER) {
                return ActionState.DRAW;
            } else if(me == Types.SCISSORS) {
                return ActionState.WIN;
            }

            return ActionState.LOSE;
        } else if(opponent == Types.ROCK) {
            if(me == Types.PAPER) {
                return ActionState.WIN;
            } else if(me == Types.SCISSORS) {
                return ActionState.LOSE;
            }

            return ActionState.DRAW;
        }

        // Scissors
        if(me == Types.PAPER) {
            return ActionState.LOSE;
        } else if(me == Types.SCISSORS) {
            return ActionState.DRAW;
        }
        return ActionState.WIN;
    }

    private static Integer getValueBasedOnTypes(Types type) {
        if(type == Types.PAPER) {
            return 2;
        } else if(type == Types.ROCK) {
            return 1;
        }

        return 3;
    }

    private static Integer getScore(char opponent, char me) {
        Types opponentType = determineType(opponent);
        Types meType = determineType(me);

        return finalizeResult(opponentType, meType);
    }

    private static int getSecondScore(char opponent, char me) {
        Types opponentType = determineType(opponent);
        Types meType = getOppositeType(opponentType, me);

        return finalizeResult(opponentType, meType);
    }

    private static int finalizeResult(Types opponentType, Types meType) {
        ActionState result = determineActionState(opponentType, meType);
        int meVal = getValueBasedOnTypes(meType);

        if(result == ActionState.DRAW) {
            return 3 + meVal;
        }

        if(result == ActionState.WIN) {
            return 6 + meVal;
        }

        return meVal;
    }
}