package life;

import javax.swing.*;
import java.awt.*;

public class JLifeTable extends JPanel {
    int width;
    int height;
    int numRow;
    int numColumn;
    int dx;
    int dy;
    Table table;

    Color live;
    Color dead;

    JLifeTable() {
        this.numRow = 0;
        this.numColumn = 0;
        this.live = Color.DARK_GRAY;
        this.dead = Color.WHITE;
    }

    public void setTable(Table t) {
        this.width = (int) Math.ceil(getWidth()*.90);
        this.height = (int) Math.ceil(getHeight()*.90);
        this.table = t;
        this.numRow = this.table.getCurrentGen().length;
        this.numColumn = this.table.getCurrentGen()[0].length;
        this.dx = (int) Math.round((double) this.width / this.numRow);
        this.dy = (int) Math.round((double) this.height /this.numColumn);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int linewidth = 2;
        int tcolum = 0;
        int trow = 0;
        for (int i = 0; i < this.width; i += this.dy) {
            for (int j = 0; j < this.height; j += this.dx) {
                if (this.table.getCurrentGen()[tcolum][trow]) {
                    g.setColor(this.live);
                    g.drawRect(i,j,this.dy,this.dx);
                    g.fillRect(i,j,this.dy,this.dx);
                } else {
                    g.setColor(this.live);
                    g.drawRect(i,j,this.dy,this.dx);
                    g.setColor(this.dead);
                    g.fillRect(i + linewidth,j + linewidth,this.dy - linewidth,this.dx - linewidth);
                }
                tcolum++;
            }
            tcolum = 0;
            trow++;
        }
    }


}
