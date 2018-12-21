package pkg2048;
public class Game extends Game_ {

    public Game() {
        setTable();
        setState();
        setScore();

        addNumber();
        addNumber();
    }
    void move(String str) {
        Movement value = Movement.valueOf(str);
        switch (value) {
            case UP:
                for (int y = 0; y < 4; y++) {
                    boolean[] jauSujungta = {false, false, false, false};
                    for (int x = 1; x < 4; x++) {
                        if (gameboard[x][y] != 0) {
                            int val = gameboard[x][y];
                            int X = x - 1;
                            while (X >= 0 && gameboard[X][y] == 0) {
                                X--;
                            }
                            if (X == -1) {
                                gameboard[0][y] = val;
                                gameboard[x][y] = 0;
                            } else if (gameboard[X][y] != val) {
                                gameboard[x][y] = 0;
                                gameboard[X + 1][y] = val;

                            } else {
                                if (jauSujungta[X]) {
                                    gameboard[x][y] = 0;
                                    gameboard[X + 1][y] = val;
                                } else {
                                    gameboard[x][y] = 0;
                                    gameboard[X][y] *= 2;
                                    score += gameboard[X][y];
                                    jauSujungta[X] = true;
                                }
                            }
                        }
                    }
                }
                break;
            case DOWN:
                for (int y = 0; y < 4; y++) {
                    boolean[] jauSujungta = {false, false, false, false};
                    for (int x = 2; x > -1; x--) {
                        if (gameboard[x][y] != 0) {
                            int val = gameboard[x][y];
                            int X = x + 1;
                            while (X <= 3 && gameboard[X][y] == 0) {
                                X++;
                            }
                            if (X == 4) {
                                gameboard[3][y] = val;
                                gameboard[x][y] = 0;
                            } else if (gameboard[X][y] != val) {
                                gameboard[x][y] = 0;
                                gameboard[X - 1][y] = val;
                            } else {
                                if (jauSujungta[X]) {
                                    gameboard[x][y] = 0;
                                    gameboard[X - 1][y] = val;
                                } else {
                                    gameboard[x][y] = 0;
                                    gameboard[X][y] *= 2;
                                    score += gameboard[X][y];
                                    jauSujungta[X] = true;
                                }
                            }
                        }
                    }
                }
                break;
            case LEFT:
                for (int x = 0; x < 4; x++) {
                    boolean[] jauSujungta = {false, false, false, false};
                    for (int y = 1; y < 4; y++) {
                        if (gameboard[x][y] != 0) {
                            int val = gameboard[x][y];
                            int Y = y - 1;
                            while (Y >= 0 && gameboard[x][Y] == 0) {
                                Y--;
                            }
                            if (Y == -1) {
                                gameboard[x][0] = val;
                                gameboard[x][y] = 0;
                            } else if (gameboard[x][Y] != val) {
                                gameboard[x][y] = 0;
                                gameboard[x][Y + 1] = val;
                            } else {
                                if (jauSujungta[Y]) {
                                    gameboard[x][y] = 0;
                                    gameboard[x][Y + 1] = val;
                                } else {
                                    gameboard[x][y] = 0;
                                    gameboard[x][Y] *= 2;
                                    score += gameboard[x][Y];
                                    jauSujungta[Y] = true;
                                }
                            }
                        }
                    }
                }
                break;
            case RIGHT:
                for (int x = 0; x < 4; x++) {
                    boolean[] jauSujungta = {false, false, false, false};
                    for (int y = 2; y > -1; y--) {
                        if (gameboard[x][y] != 0) {
                            int val = gameboard[x][y];
                            int Y = y + 1;
                            while (Y <= 3 && gameboard[x][Y] == 0) {
                                Y++;
                            }
                            if (Y == 4) {
                                gameboard[x][3] = val;
                                gameboard[x][y] = 0;
                            } else if (gameboard[x][Y] != val) {
                                gameboard[x][y] = 0;
                                gameboard[x][Y - 1] = val;
                            } else {
                                if (jauSujungta[Y]) {
                                    gameboard[x][y] = 0;
                                    gameboard[x][Y - 1] = val;

                                } else {
                                    gameboard[x][y] = 0;
                                    gameboard[x][Y] *= 2;
                                    score += gameboard[x][Y];
                                    jauSujungta[Y] = true;
                                }
                            }
                        }
                    }
                }
               break;
        }
    }
}

  

    
