package pkg2048;

import java.util.ArrayList;
import java.util.Random;
public class Game_ {
    protected int[][] gameboard;
    protected int score;
    private Random r = new Random();
    private State state;

    public void setTable(){
        gameboard = new int[4][4];
    }

    public int[][] getTable() {
        return gameboard;
    }

    public void setState() {
        state = State.CONTINUE;
    }

    public State getState() {
        return state;
    }

    public void setScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    void addNumber() {
        if (checkBoardFull()) {
            return;
        }
        int choice;
        int new_number = 2;
        int random_number;
        int X;
        int Y;
        ArrayList<Integer> empty_places_X = new ArrayList<>();
        ArrayList<Integer> empty_places_Y = new ArrayList<>();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (gameboard[x][y] == 0) {
                    empty_places_X.add(x);
                    empty_places_Y.add(y);
                }
            }
        }
        choice = r.nextInt(empty_places_X.size());
        random_number = r.nextInt(10);  //0-9
        if (random_number == 0)
            new_number = 4;

        X = empty_places_X.get(choice);
        Y = empty_places_Y.get(choice);
        gameboard[X][Y] = new_number;
    }
    boolean checkFor2048() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (gameboard[x][y] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }
    boolean checkBoardFull() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (gameboard[x][y] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean areThereMoves() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (x == 0) {
                    if (y != 0) {
                        if (gameboard[x][y] == gameboard[x][y - 1]) {
                            return true;
                        }
                    }
                } else {
                    if (y != 0) {
                        if (gameboard[x][y] == gameboard[x][y - 1]) {
                            return true;
                        }
                    }
                    if (gameboard[x][y] == gameboard[x - 1][y]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    void updateState() {
        if (checkFor2048()) {
            state = State.WIN;
        } else if (checkBoardFull()) {
            state = areThereMoves() ? State.CONTINUE : State.LOSE;
        } else {
            state = State.CONTINUE;
        }
    }
}
