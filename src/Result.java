import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'missingArrow' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER size
     *  2. STRING targets
     *  3. STRING numbers
     *  4. STRING arrows
     */

    public static String missingArrow(int size, String targets, String numbers, String arrows) {
        int[][] board = new int[size][size];
        int[] rowNum = new int[size];
        int[] colNum = new int[size];
        ArrayList<Integer> targetRow = new ArrayList<Integer>();
        ArrayList<Integer> targetCol = new ArrayList<Integer>();
        ArrayList<Integer> arrowRow = new ArrayList<Integer>();
        ArrayList<Integer> arrowCol = new ArrayList<Integer>();
        ArrayList<String> arrowAngle = new ArrayList<String>();
        String result = "";
        int lastRow = 0;
        int lastCol = 0;
        int errorRow = 0;
        int errorCol = 0;

        /*
         * row and col for targets
         */
        while(targets.length() > 0)
        {
            targetRow.add(Integer.parseInt(targets.substring(0, 1)));
            targetCol.add(Integer.parseInt(targets.substring(1, 2)));
            if(targets.length() == 2)
            {
                break;
            }
            targets = targets.substring(3);
        }

        /*
         * set target as int 1
         */
        for(int i = 0; i < targetRow.size(); i++)
        {
            board[targetRow.get(i)][targetCol.get(i)] = 1;
        }

        /*
         * number for col and row
         */
        for(int i = 0; i < size; i++)
        {
            rowNum[i] = Integer.parseInt(numbers.substring(i, i + 1));
            colNum[i] = Integer.parseInt(numbers.substring(i + size + 1, i + size + 2));
        }

        /*
         * arrow info
         */
        while(arrows.length() > 0)
        {
            arrowRow.add(Integer.parseInt(arrows.substring(0, 1)));
            arrowCol.add(Integer.parseInt(arrows.substring(1, 2)));
            arrowAngle.add(arrows.substring(2, 3));
            if(arrows.length() == 3)
            {
                break;
            }
            arrows = arrows.substring(4);
        }

        /*
         * eliminating arrowed targets
         */
        for(int i = 0; i < arrowAngle.size(); i++)
        {
            if(arrowAngle.get(i).equals("A"))
            {
                for(int c = arrowCol.get(i); c >= 0; c--)
                {
                    if(board[arrowRow.get(i)][c] == 1)
                    {
                        targetRow.remove((Integer)arrowRow.get(i));
                        targetCol.remove((Integer)c);
                        board[arrowRow.get(i)][c] = 0;
                        rowNum[arrowRow.get(i)]--;
                        colNum[arrowCol.get(i)]--;
                        c = -1;
                    }
                }
            }
            else if(arrowAngle.get(i).equals("B"))
            {
                for(int r = arrowRow.get(i); r >= 0; r--)
                {
                    if(board[r][arrowCol.get(i)] == 1)
                    {
                        targetRow.remove((Integer)r);
                        targetCol.remove((Integer)arrowCol.get(i));
                        board[r][arrowCol.get(i)] = 0;
                        rowNum[arrowRow.get(i)]--;
                        colNum[arrowCol.get(i)]--;
                        r = -1;
                    }
                }
            }
            else if(arrowAngle.get(i).equals("C"))
            {
                for(int c = arrowCol.get(i); c < board[0].length; c++)
                {
                    if(board[arrowRow.get(i)][c] == 1)
                    {
                        targetRow.remove((Integer)arrowRow.get(i));
                        targetCol.remove((Integer)c);
                        board[arrowRow.get(i)][c] = 0;
                        rowNum[arrowRow.get(i)]--;
                        colNum[arrowCol.get(i)]--;
                        c = board[0].length;
                    }
                }
            }
            else if(arrowAngle.get(i).equals("D"))
            {
                for(int r = arrowRow.get(i); r < board.length; r++)
                {
                    if(board[r][arrowCol.get(i)] == 1)
                    {
                        targetRow.remove((Integer)r);
                        targetCol.remove((Integer)arrowCol.get(i));
                        board[r][arrowCol.get(i)] = 0;
                        rowNum[arrowRow.get(i)]--;
                        colNum[arrowCol.get(i)]--;
                        r = board.length;
                    }
                }
            }
            else if(arrowAngle.get(i).equals("E"))
            {
                int row = arrowRow.get(i);
                int col = arrowCol.get(i);
                while(row >= 0 && col >= 0)
                {
                    if(board[row][col] == 1)
                    {
                        targetRow.remove((Integer)row);
                        targetCol.remove((Integer)col);
                        board[row][col] = 0;
                        rowNum[arrowRow.get(i)]--;
                        colNum[arrowCol.get(i)]--;
                        row = 0;
                    }
                    row--;
                    col--;
                }
            }
            else if(arrowAngle.get(i).equals("F"))
            {
                int row = arrowRow.get(i);
                int col = arrowCol.get(i);
                while(row >= 0 && col < board[0].length)
                {
                    if(board[row][col] == 1)
                    {
                        targetRow.remove((Integer)row);
                        targetCol.remove((Integer)col);
                        board[row][col] = 0;
                        rowNum[arrowRow.get(i)]--;
                        colNum[arrowCol.get(i)]--;
                        row = 0;
                    }
                    row--;
                    col++;
                }
            }
            else if(arrowAngle.get(i).equals("G"))
            {
                int row = arrowRow.get(i);
                int col = arrowCol.get(i);
                while(row < board.length && col < board[0].length)
                {
                    if(board[row][col] == 1)
                    {
                        targetRow.remove((Integer)row);
                        targetCol.remove((Integer)col);
                        board[row][col] = 0;
                        rowNum[arrowRow.get(i)]--;
                        colNum[arrowCol.get(i)]--;
                        row = board.length;
                    }
                    row++;
                    col++;
                }
            }
            else if(arrowAngle.get(i).equals("H"))
            {
                int row = arrowRow.get(i);
                int col = arrowCol.get(i);
                while(row < board.length && col >= 0)
                {
                    if(board[row][col] == 1)
                    {
                        targetRow.remove((Integer)row);
                        targetCol.remove((Integer)col);
                        board[row][col] = 0;
                        rowNum[arrowRow.get(i)]--;
                        colNum[arrowCol.get(i)]--;
                        row = board.length;
                    }
                    row++;
                    col--;
                }
            }
        }

        for(int i = 0; i < rowNum.length; i++)
        {
            if(rowNum[i] > 0)
            {
                lastRow = i;
            }
        }

        for(int i = 0; i < colNum.length; i++)
        {
            if(colNum[i] > 0)
            {
                lastCol = i;
            }
        }

        errorRow = targetRow.get(0) - lastRow;
        errorCol = targetCol.get(0) - lastCol;

        if(errorRow == 0 && errorCol < 0)
        {
            result = "A";
        }
        else if(errorRow < 0 && errorCol == 0)
        {
            result = "B";
        }
        else if(errorRow == 0 && errorCol > 0)
        {
            result = "C";
        }
        else if(errorRow > 0 && errorCol == 0)
        {
            result = "D";
        }
        else if(errorRow < 0 && errorCol < 0)
        {
            result = "E";
        }
        else if(errorRow < 0 && errorCol > 0)
        {
            result = "F";
        }
        else if(errorRow > 0 && errorCol > 0)
        {
            result = "G";
        }
        else if(errorRow > 0 && errorCol < 0)
        {
            result = "H";
        }

        return Integer.toString(lastRow) + Integer.toString(lastCol) + result;
    }
}