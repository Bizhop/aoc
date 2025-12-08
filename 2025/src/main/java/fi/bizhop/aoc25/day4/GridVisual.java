package fi.bizhop.aoc25.day4;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class GridVisual extends JPanel {
    private final List<List<Boolean>> data;

    public GridVisual(List<List<Boolean>> data) {
        this.data = data;

        JFrame frame = new JFrame("Grid visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(1360, 1360);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = 10;

        for (int row = 0; row < data.size(); row++) {
            for (int col = 0; col < data.get(row).size(); col++) {
                g.setColor(data.get(row).get(col) ? Color.BLACK : Color.WHITE);
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }

    public void updateCell(int row, int col, Boolean value) {
        this.data.get(row).set(col, value);
        repaint();
    }
}
