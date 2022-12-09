package com.wankish.day5;

import com.wankish.utils.Logger;

import java.util.*;

public class Matrix {
    public static final int COUNT = 9;
    private final HashMap<Integer, ArrayList<String>> columns;

    public Matrix() {
        this.columns = new HashMap<>();

        for(int i = 1; i <= COUNT; i++) {
            columns.put(i, new ArrayList<>());
        }
    }

    public void moveFromAction(Action action) {
        ArrayList<String> from = columns.get(Integer.parseInt(action.from()));
        ArrayList<String> to = columns.get(Integer.parseInt(action.to()));

        for(int i = 1; i <= action.moveCount(); i++) {
            String first = from.get(0);
            to.add(0, first);
            from.remove(0);
        }
    }

    public void moveFromAction2(Action action) {
        ArrayList<String> from = columns.get(Integer.parseInt(action.from()));
        ArrayList<String> to = columns.get(Integer.parseInt(action.to()));

        ArrayList<String> movable = new ArrayList<>();
        int index = 0;

        for(int i = 1; i <= action.moveCount(); i++) {
            String first = from.get(0);
            movable.add(index, first);
            from.remove(0);
        }

        Collections.reverse(movable);

        for(int i = 0; i < movable.size(); i++) {
            to.add(i, movable.get(i));
        }
    }

    public void printFirstElements() {
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i<= COUNT; i++) {
            ArrayList<String> current = columns.get(i);
            String currentElement = current.get(0);
            builder.append(currentElement.charAt(1));
        }

        Logger.log(builder);
    }

    public void loadToMatrix(String set) {
        parseRow(set);
    }

    private void parseRow(String set) {
        int addableIndex = 1;
        StringBuilder builder = new StringBuilder();
        boolean canAdd = false;
        boolean skipped = false;
        for(int i = 0; i < set.length(); i++) {
            char current = set.charAt(i);
            if(canAdd) {
                builder.append(current);
            }

            if(current == '[') {
                canAdd = true;
                builder.append(current);
                skipped = false;
            } else if(current == ']') {
                canAdd = false;
                ArrayList<String> currentCol = columns.get(addableIndex);
                currentCol.add(builder.toString());
                builder.delete(0, builder.length());
                addableIndex++;
            } else if(skipped && current == ' ') {
                skipped = false;
                addableIndex++;
                // intentional to skip the second 2 spaces
                i += 2;
            } else if(current == ' ') {
                skipped = true;
            }
        }
    }
}
