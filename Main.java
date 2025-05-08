import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
class Main extends JFrame{
    public static void main(String[]args){
        new Main();
    }
    Main(){
        Dimension d=new Dimension(1024,1024);
        this.setPreferredSize(d);
        this.setLocation(0,0);
        this.setLayout(new BorderLayout());
        GraphicsEngine g=new GraphicsEngine(this,d);
        while(true){}
    }
}
