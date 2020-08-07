package ui;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    public void paintComponent(Graphics g) {
        Image image = new ImageIcon("./data/sonic.jpg").getImage();
        g.drawImage(image,20,20,this);
    }

}
