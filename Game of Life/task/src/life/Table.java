package life;
public abstract class Table {
    boolean currentGen[][];
    boolean lastGen[][];
    char aliveSymbol;
    char deadSymbol;
    GenerateAlgorithm algorithm;

    public void nextGeneration() {
        this.lastGen = this.currentGen;
        this.currentGen = algorithm.updateTable(this.currentGen);
    }

    public void setAlgorithm(GenerateAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public boolean[][] getCurrentGen() { return this.currentGen;}


    public int getNumberOfAliveCells() {
        int cont = 0;
        for (boolean[] row : this.currentGen) {
            for (boolean x :
                    row) {
                if (x) {
                    cont++;
                }
            }
        }
        return cont;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean[] x:
                this.currentGen) {
            for (boolean y:
                    x) {
                sb.append(y ? aliveSymbol : deadSymbol);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}