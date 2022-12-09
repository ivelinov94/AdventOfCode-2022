package com.wankish.day5;

import com.wankish.utils.FileReader;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public  static void main(String[] args) throws URISyntaxException {
        FileReader reader = new FileReader();
        List<String> dataset = reader.readFile("day5.txt");

        ArrayList<Action> actions = new ArrayList<>();
        Matrix matrix = new Matrix();
        Matrix matrix2 = new Matrix();

        for(String set: dataset) {
            boolean isAction = isAction(set);

            if(isAction) {
                actions.add(parseAction(set));
            } else if(!Objects.equals(set, "")) {
                matrix.loadToMatrix(set);
                matrix2.loadToMatrix(set);
            }
        }

        for(Action action: actions) {
            matrix.moveFromAction(action);
            matrix2.moveFromAction2(action);
        }

        matrix.printFirstElements();
        matrix2.printFirstElements();
    }

    private static boolean isAction(String set) {
        return set.startsWith("move");
    }

    private static Action parseAction(String set) {
        // move 2 from 1 to 7
        String[] split = set.split(" ");
        int moveCount = Integer.parseInt(split[1]);
        String from = split[3];
        String to = split[5];
        return new Action(moveCount, from, to);
    }
}
