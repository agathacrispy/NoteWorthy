package ui;

import model.LyricLine;
import javax.swing.JPanel;
import java.util.List;

public class GameplayPanel extends JPanel {

    private LyricLine currentLine;
    private LyricLine nextLine;

    public void setLines(LyricLine current, LyricLine next) {
        this.currentLine = current;
        this.nextLine = next;
        repaint();
    }
}
