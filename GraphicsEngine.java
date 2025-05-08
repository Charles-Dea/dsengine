import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
class GraphicsEngine extends JPanel{
    @Override
    protected void paintComponent(Graphics gr){
        Graphics2D g=(Graphics2D)gr;
        g.setColor(Color.RED);
        g.fillOval(200,200,512,512);
        repaint();
    }
    GraphicsEngine(Main w,Dimension d){
        w.add(this,BorderLayout.CENTER);
    }
}
