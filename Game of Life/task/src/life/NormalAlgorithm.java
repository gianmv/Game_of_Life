package life;


import java.util.ArrayList;

public class NormalAlgorithm implements GenerateAlgorithm {
    @Override
    public boolean[][] updateTable(boolean[][] table) {
        boolean[][] ans = new boolean[table.length][table[0].length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                ans[i][j] = checkNeighbor(table, i, j);
            }
        }

        return ans;
    }



    private boolean checkNeighbor(boolean[][] table, int i, int j) {
        int numRows = table.length;
        int numColum = table[0].length;
        boolean actualCell = table[i][j];
        ArrayList<Boolean> check = new ArrayList<>();
        check.add(table[Math.floorMod(i - 1, numRows)][Math.floorMod(j - 1, numColum)]);
        check.add(table[Math.floorMod(i - 1, numRows)][Math.floorMod(j, numColum)]);
        check.add(table[Math.floorMod(i - 1, numRows)][Math.floorMod(j + 1, numColum)]);
        check.add(table[Math.floorMod(i, numRows)][Math.floorMod(j + 1, numColum)]);
        check.add(table[Math.floorMod(i + 1, numRows)][Math.floorMod(j + 1, numColum)]);
        check.add(table[Math.floorMod(i + 1, numRows)][Math.floorMod(j, numColum)]);
        check.add(table[Math.floorMod(i + 1, numRows)][Math.floorMod(j - 1, numColum)]);
        check.add(table[Math.floorMod(i, numRows)][Math.floorMod(j - 1, numColum)]);

        int cont = 0;
        for (boolean x:
                check) {
            if (x) {
                cont++;
            }
        }

        if (actualCell) {
            return !(cont < 2 || cont > 3);
        } else {
            return cont == 3;
        }
    }
}