package RoundedBorders;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.util.Formatter;

public class RoundedFormattedTextField extends JFormattedTextField {
    public RoundedFormattedTextField(MaskFormatter maskFormatter) {
	   super(maskFormatter);
	   setOpaque(false);
    }

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
	   setBorder(new RoundedCornerBorder());
    }
    public RoundedFormattedTextField(Formatter formatter){
        super(formatter);
        setOpaque(false);
    }
    public RoundedFormattedTextField(){
        setOpaque(false);
    }
}
