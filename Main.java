import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
class Main extends JFrame{
    public static void main(String[]args){
        new Main();
    }
    Main(){
        Dimension d=new Dimension(512,512);
        setPreferredSize(d);
        setLayout(new BorderLayout());
        GraphicsEngine g=new GraphicsEngine(this,d);
        setVisible(true);
        //g.repaint();
    }
}
