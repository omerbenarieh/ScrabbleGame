package test;


import java.util.ArrayList;

public class Board {
    Tile[][] board;
    private static Board b;
    String[][] colors;
    ArrayList<Word> allWords;

    public Board() {
        board = new Tile[15][15];
        colors = new String[15][15];

        allWords = new ArrayList<Word>();

        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++)
                colors[i][j] = "";


        colors[7][7] = "Star";

        colors[0][0] = "Red";
        colors[7][0] = "Red";
        colors[14][0] = "Red";
        colors[0][7] = "Red";
        colors[14][7] = "Red";
        colors[0][14] = "Red";
        colors[7][14] = "Red";
        colors[14][14] = "Red";

        colors[1][1] = "Yellow";
        colors[2][2] = "Yellow";
        colors[3][3] = "Yellow";
        colors[4][4] = "Yellow";
        colors[13][1] = "Yellow";
        colors[12][2] = "Yellow";
        colors[11][3] = "Yellow";
        colors[10][4] = "Yellow";
        colors[1][13] = "Yellow";
        colors[2][12] = "Yellow";
        colors[3][11] = "Yellow";
        colors[4][10] = "Yellow";
        colors[10][10] = "Yellow";
        colors[11][11] = "Yellow";
        colors[12][12] = "Yellow";
        colors[13][13] = "Yellow";

        colors[3][0] = "LightBlue";
        colors[11][0] = "LightBlue";
        colors[6][2] = "LightBlue";
        colors[8][2] = "LightBlue";
        colors[0][3] = "LightBlue";
        colors[7][3] = "LightBlue";
        colors[14][3] = "LightBlue";
        colors[2][6] = "LightBlue";
        colors[6][6] = "LightBlue";
        colors[8][6] = "LightBlue";
        colors[12][6] = "LightBlue";
        colors[3][7] = "LightBlue";
        colors[11][7] = "LightBlue";
        colors[2][8] = "LightBlue";
        colors[6][8] = "LightBlue";
        colors[8][8] = "LightBlue";
        colors[12][8] = "LightBlue";
        colors[0][11] = "LightBlue";
        colors[7][11] = "LightBlue";
        colors[14][11] = "LightBlue";
        colors[6][12] = "LightBlue";
        colors[8][12] = "LightBlue";
        colors[3][14] = "LightBlue";
        colors[11][14] = "LightBlue";

        colors[5][1] = "Blue";
        colors[9][1] = "Blue";
        colors[1][5] = "Blue";
        colors[5][5] = "Blue";
        colors[9][5] = "Blue";
        colors[13][5] = "Blue";
        colors[1][9] = "Blue";
        colors[5][9] = "Blue";
        colors[9][9] = "Blue";
        colors[13][9] = "Blue";
        colors[5][13] = "Blue";
        colors[9][13] = "Blue";
    }

    public static Board getBoard() {
        if (b == null)
            b = new Board();
        return b;
    }

    public Tile[][] getTiles() {
        return this.board.clone();
    }

    public boolean dictionaryLegal(Word w) {
        return true;
    }

    public boolean boardLegal(Word w) {
        if (allInBoard(w)) {
            if (boardIsEmpty())
                return onStar(w);
            return notOnStar(w);
        }
        return false;
    }

    // helper method
    private boolean allInBoard(Word w) {
        return w.tiles.length < 16;
    }

    // helper method
    private boolean boardIsEmpty() {
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++)
                if (board[i][j] != null)
                    return false;
        return true;
    }

    // helper method
    private boolean notOnStar(Word w) {
        int length = w.tiles.length;
        int row = w.row;
        int col = w.col;
        boolean vertical = w.vertical;

        for (int i = 0; i < length; i++) {
            if (w.tiles[i] == null)
                return onTile(w);
        }
        return nearTile(w);
    }

    // helper method
    private boolean onTile(Word w) {
        int length = w.tiles.length;
        int row = w.row;
        int col = w.col;
        boolean vertical = w.vertical;

        if (vertical) {
            for (int i = 0; i < length; i++) {
                if (board[row + i][col] != null)
                    if (w.tiles[i] != null)
                        return false;
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (board[row][col + i] != null)
                    if (w.tiles[i] != null)
                        return false;
            }
        }
        return true;
    }

    // helper method
    private boolean nearTile(Word w) {

        int length = w.tiles.length;
        int row = w.row;
        int col = w.col;
        boolean vertical = w.vertical;

        boolean up = false, down = false, right = false, left = false;

        if (vertical) {
            if (row + length < 15)
                for (int i = 0; i < length; i++) {
                    if (board[row + i][col] != null)
                        return false;
                }

            // UP
            if (row != 0)
                if (board[row - 1][col] != null)
                    up = true;

            // Down
            if (row + length <= 15)
                if (board[row + length][col] != null)
                    down = true;


            for (int i = 0; i < length; i++) {
                // Right
                if (board[row + i][col + 1] != null) {
                    right = true;
                }

                // Left
                if (board[row + i][col - 1] != null) {
                    left = true;
                }
            }

        } else {
            if (col + length < 15) {
                for (int i = 0; i < length; i++) {
                    if (board[row][col + i] != null)
                        return false;
                }


                for (int i = 0; i < length; i++) {

                    // Up
                    if (board[row - 1][col + i] != null) {
                        up = true;
                    }

                    // Down
                    if (board[row + 1][col + i] != null) {
                        down = true;
                    }
                }
            }


            // Left
            if (col != 0) {
                if (board[row][col - 1] != null)
                    left = true;
            }

            // Right
            if (col + length < 15)
                if (board[row][col + length] != null)
                    right = true;

        }
        return up || down || right || left;
    }


    // helper method
    private boolean onStar(Word w) {
        int length = w.tiles.length;
        int row = w.row;
        int col = w.col;
        boolean vertical = w.vertical;

        if (col < 0 || row < 0)
            return false;
        for (int i = 0; i < length; i++)
            if (w.tiles[i] == null)
                return false;

        if (vertical) {
            if (col == 7 && row + length <= 15)
                return row + length >= 8 && row <= 7;
        } else {
            if (row == 7 && col + length <= 15)
                return col + length >= 8 && col <= 7;
        }
        return false;
    }

    public int getScore(Word w) {
        int length = w.tiles.length;
        int col = w.col;
        int row = w.row;
        boolean vertical = w.vertical;


        int wordVal = 0;
        if (vertical)
            for (int i = 0; i < length; i++) {
                if (w.tiles[i] != null) {
                    if (colors[row + i][col].equals("LightBlue"))
                        wordVal += w.tiles[i].score * 2;
                    else if (colors[row + i][col].equals("Blue")) {
                        wordVal += w.tiles[i].score * 3;
                    } else
                        wordVal += w.tiles[i].score;
                } else {
                    if (colors[row + i][col].equals("LightBlue"))
                        wordVal += board[row + i][col].score * 2;
                    else if (colors[row + i][col].equals("Blue"))
                        wordVal += board[row + i][col].score * 3;
                    else
                        wordVal += board[row + i][col].score;
                }

            }
        else {
            for (int i = 0; i < length; i++) {
                if (w.tiles[i] != null) {
                    if (colors[row][col + i].equals("LightBlue"))
                        wordVal += w.tiles[i].score * 2;
                    else if (colors[row][col + i].equals("Blue")) {
                        wordVal += w.tiles[i].score * 3;
                    } else wordVal += w.tiles[i].score;
                } else {
                    if (colors[row + i][col].equals("LightBlue"))
                        wordVal += board[row][col + i].score * 2;
                    else if (colors[row + i][col].equals("Blue"))
                        wordVal += board[row][col + i].score * 3;
                    else
                        wordVal += board[row][col + i].score;
                }
            }
        }

        int finalWordValue = wordVal;

        if (vertical) {
            for (int i = 0; i < length; i++) {
                if (w.tiles[i] != null) {
                    if (colors[row + i][col].equals("Red"))
                        finalWordValue *= 3;
                    if (colors[row + i][col].equals("Star") && boardIsEmpty())
                        finalWordValue *= 2;
                    if (colors[row + i][col].equals("Yellow"))
                        finalWordValue *= 2;
                } else {
                    if (colors[row + i][col].equals("Red"))
                        finalWordValue *= 3;
                    else if (colors[row + i][col].equals("Star") && boardIsEmpty() || colors[row + i][col].equals("Yellow"))
                        finalWordValue *= 2;
                }
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (w.tiles[i] != null) {
                    if (colors[row][col + i].equals("Red"))
                        finalWordValue *= 3;
                    if (colors[row][col + i].equals("Star") && boardIsEmpty())
                        finalWordValue *= 2;
                    if (colors[row][col + i].equals("Yellow"))
                        finalWordValue *= 2;
                } else {
                    if (colors[row][col + i].equals("Red"))
                        finalWordValue *= 3;
                    else if (colors[row][col + i].equals("Star") && boardIsEmpty() || colors[row][col + i].equals("Yellow"))
                        finalWordValue *= 2;
                }
            }
        }
        return finalWordValue;
    }

    // helper method
    private Word getFullWord(Word w) {
        int length = w.tiles.length;
        Tile[] t = new Tile[length];
        if (w.vertical) {
            for (int i = 0; i < length; i++) {
                if (w.tiles[i] == null)
                    t[i] = board[w.row + i][w.col];
                else
                    t[i] = w.tiles[i];
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (w.tiles[i] == null)
                    t[i] = board[w.row][w.col + i];
                else
                    t[i] = w.tiles[i];
            }
        }
        return new Word(t, w.row, w.col, w.vertical);
    }

    public ArrayList<Word> getWords(Word w) {
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(getFullWord(w));

        if (w.vertical) {

            // Up and down vertical
            if (upDownVertical(w) != null)
                words.add(upDownVertical(w));


            // Right and left Tile vertical
            for (int i = 0; i < w.tiles.length; i++) {
                if (rightLeftTileVertical(w, i, w.tiles[i]) != null)
                    words.add(rightLeftTileVertical(w, i, w.tiles[i]));
            }


        } else {
            // Right and left not vertical
            if (rightLeftNotVertical(w) != null)
                words.add(rightLeftNotVertical(w));

            // Up and down Tile not vertical
            for (int i = 0; i < w.tiles.length; i++) {
                Word u = upDownTileNotVertical(w, i, w.tiles[i]);
                if (u != null)
                    words.add(u);
            }
        }
        this.allWords.addAll(words);
        return words;
    }

    // helper method
    private Word upDownTileNotVertical(Word w, int index, Tile tile) {
        int length = 1;
        int col = w.col + index;
        int row = w.row;
        boolean vertical = w.vertical;


        if (w.tiles[index] != null) {
            if (board[row + 1][col] != null || board[row - 1][col] != null) {

                // Up
                int i = 0;
                while (board[row - length - i][col] != null) {
                    i++;
                }

                // Down
                int z = 0;
                while (board[row + length + z][col] != null) {
                    z++;
                }

                row -= i;
                length += i + z;

                Tile[] t = new Tile[length];

                for (int j = 0; j < t.length; j++) {
                    if (board[row + j][col] != null)
                        t[j] = board[row + j][col];
                    else
                        t[j] = tile;
                }

                Word newWord = new Word(t, row, col, true);
                if (this.allWords.contains(newWord))
                    return null;
                return newWord;
            }
        }
        return null;
    }

    // helper method
    private Word rightLeftTileVertical(Word w, int index, Tile tile) {
        int length = 1;
        int col = w.col;
        int row = w.row + index;
        boolean vertical = w.vertical;

        if (board[row][col + 1] != null || board[row][col - 1] != null) {

            // Right
            int i = 0;
            while (board[row][col + length + i] != null) {
                i++;
                if (col + i == 14)
                    break;
            }

            // Left
            int z = 0;
            while (board[row][col - length - z] != null) {
                z++;
                if (col - z == 0)
                    break;
            }

            col = col - z;
            length += i + z;
            Tile[] t = new Tile[length];

            for (int j = 0; j < t.length; j++) {
                if (board[row][col + j] != null)
                    t[j] = board[row][col + j];
                else
                    t[j] = tile;
            }

            Word newWord = new Word(t, row, col, false);
            if (this.allWords.contains(newWord))
                return null;
            return newWord;
        }
        return null;
    }

    // helper method
    private Word rightLeftNotVertical(Word w) {

        int length = w.tiles.length;
        int row = w.row;
        int col = w.col;
        boolean vertical = w.vertical;

        if (col != 0 && col + length != 15) {
            if (board[row][col - 1] != null || board[row][col + length] != null) {
                int i = 0;
                while (board[row][col - i - 1] != null) {
                    i++;
                }
                int z = 0;
                while (board[row][col + length + z] != null) {
                    z++;
                }
                col = col - i;
                length += i + z;

                Tile[] t = new Tile[length];
                int h;
                for (h = 0; h < w.tiles.length; h++) {
                    t[h] = w.tiles[h];
                }

                for (int j = h; j < t.length; j++) {
                    t[j] = board[row][col + j];
                }

                Word newWord = new Word(t, row, col, vertical);
                if (this.allWords.contains(newWord))
                    return null;
                return newWord;
            }
        } else if (col == 0 && col + length < 15) {
            int z = 0;
            while (board[row][col + length + z] != null) {
                z++;
            }

            length += z;
            Tile[] t = new Tile[length];
            int h;
            for (h = 0; h < w.tiles.length; h++) {
                t[h] = w.tiles[h];
            }

            for (int j = h; j < t.length; j++) {
                t[j] = board[row][col + j];
            }

            Word newWord = new Word(t, row, col, vertical);
            if (this.allWords.contains(newWord))
                return null;
            return newWord;
        } else {
            int i = 0;
            // new word Length
            while (board[row][col - i] != null) {
                i++;
            }
            col = col - i + 1;
            length += i;
            int count = 0;
            for (int u = 0; u < w.tiles.length; u++) {
                if (w.tiles[u] == null) {
                    length--;
                    count++;
                }
            }
            Tile[] t = new Tile[length];

            int h;
            for (h = 0; h < length - w.tiles.length + count; h++) {
                if (board[row][col + h] != null)
                    t[h] = board[row][col + h];
            }

            int s = 1;
            for (int j = h; j < t.length; j++, s++) {
                if (t[j] == null)
                    t[j] = w.tiles[s];
            }

            Word newWord = new Word(t, row, col, vertical);
            if (this.allWords.contains(newWord))
                return null;
            return newWord;
        }
        return null;
    }

    // helper method
    private Word upDownVertical(Word w) {
        int length = w.tiles.length;
        int row = w.row;
        int col = w.col;
        boolean vertical = w.vertical;


        if (board[row - 1][col] != null || board[row + length][col] != null) {
            int i = 0;
            while (board[row - i - 1][col] != null) {
                i++;
            }
            int z = 0;
            while (board[row + length + z][col] != null) {
                z++;
            }


            row = row - i;
            length += i + z;

            Tile[] t = new Tile[length];
            int j;
            int s = 0;
            for (j = 0; j < t.length; j++) {
                if (board[row + j][col] != null)
                    t[j] = board[row + j][col];
                else if (w.tiles[0] == null) {
                    t[j] = w.tiles[s + 1];
                    s++;
                } else {
                    t[j] = w.tiles[s];
                    s++;
                }
            }
            Word newWord = new Word(t, row, col, vertical);
            if (this.allWords.contains(newWord))
                return null;
            return newWord;
        }
        return null;
    }


    public int tryPlaceWord(Word w) {
        int score = 0;

        if (boardLegal(w)) {
            ArrayList<Word> newWords = getWords(w);
            for (int i = 0; i < newWords.size(); i++) {
                if (dictionaryLegal(newWords.get(i)))
                    score += getScore(newWords.get(i));
            }
            placeWord(w);
        }
        return score;
    }

    // helper method
    private void placeWord(Word w) {
        if (w.vertical) {
            for (int i = 0; i < w.tiles.length; i++)
                if (w.tiles[i] != null)
                    board[w.row + i][w.col] = w.tiles[i];
        } else {
            for (int i = 0; i < w.tiles.length; i++)
                if (w.tiles[i] != null)
                    board[w.row][w.col + i] = w.tiles[i];
        }
    }
}

