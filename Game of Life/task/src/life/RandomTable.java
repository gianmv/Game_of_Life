package life;
import java.util.Random;

public class RandomTable extends Table {
    Random randomGenerator;

    RandomTable(int rows, int columns, char aliveSymbol, char deadSymbol) {
        this.currentGen = new boolean[rows][columns];
        this.lastGen = new boolean[rows][columns];
        this.randomGenerator = new Random();
        this.aliveSymbol = aliveSymbol;
        this.deadSymbol = deadSymbol;
        updateTable();
    }

    public void setRandomSeed(int seed) {
        this.randomGenerator = new Random(seed);
        updateTable();
    }

    private void updateTable() {
        for (int i = 0; i < this.currentGen.length; i++) {
            for (int j = 0; j < this.currentGen[i].length; j++) {
                this.currentGen[i][j] = this.randomGenerator.nextBoolean();
            }
        }
    }

    @Override
    public void nextGeneration() {
        super.nextGeneration();
    }

}
