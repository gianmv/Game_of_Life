package life;

public class SimpleTable extends Table{

    SimpleTable(boolean table[][]) {
        this.currentGen = table;
        this.aliveSymbol = '0';
        this.deadSymbol = ' ';
    }

    @Override
    public int getNumberOfAliveCells() {
        return 0;
    }
}
