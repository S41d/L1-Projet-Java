package RoundedBorders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoundedButton extends JButton {
    @Override protected void paintComponent(Graphics g) {
	   if (! isOpaque() && (getBorder() instanceof RoundedCornerBorder)) {
		  Graphics2D g2 = (Graphics2D) g.create();
		  g2.setPaint(getBackground());
		  g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
		  g2.dispose();
	   }
	   super.paintComponent(g);
    }

    @Override public void updateUI() {
	   super.updateUI();
	   setOpaque(false);
	   setContentAreaFilled(false);
	   setBorder(new RoundedCornerBorder());
    }


    public RoundedButton(String text){
        super(text);
        setFocusPainted(false);
    }
}
