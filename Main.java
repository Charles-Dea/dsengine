import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.HashMap;
class Main extends JFrame{
    private String p;
    public static void main(String[]args){
        new Main(args[0]);
    }
    public String abspth(String p){
        return this.p+p;
    }
    private Main(String p){
        this.p=p;
        if(p.charAt(p.length()-1)!='/')this.p+='/';
        Dictionary go=new Dictionary();
        HashMap<String,Variable>nm=new HashMap<String,Variable>();
        nm.put("go",go);
        Script is=new Script("init.csl",this,nm);
        is.run("start");
        setSize(1024,1024);
        setLayout(new BorderLayout());
        GraphicsEngine g=new GraphicsEngine(go,this);
        add(g,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
