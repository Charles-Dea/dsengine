import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
class Script{
    private enum Command{
        DEF,
        DS,
        STOP
    }
    private class Id extends Variable{
        String v;
        public Id(String v){
            this.t=Type.ID;
            this.v=new String(v);
        }
    }
    private class Instruction{
        public Command c;
        public Variable[]o;
        public Instruction(Command c,Variable[]o){
            int ol=o.length;
            switch(c){
                case DEF:
                    if(ol!=2){
                        System.err.println("Invalid number of parameters to def: "+ol);
                        System.exit(-1);
                    }
                    if(o[0].t!=Variable.Type.ID){
                        System.err.print("Variable name may not be int or string\n");
                        System.exit(-1);
                    }
                    break;
                case DS:
                    if(ol!=3){
                        System.err.println("Invalid number of parameters to ds: "+ol);
                        System.exit(-1);
                    }
                    if(o[0].t!=Variable.Type.ID){
                        System.err.print("Dictionary name may not be a string or int\n");
                        System.exit(-1);
                    }
                    break;
                case STOP:
                    if(ol!=0){
                        System.err.println("Invalid number of parameters to ds: "+ol);
                        System.exit(-1);
                    }
            }
            this.c=c;
            this.o=o;
        }
    }
    public HashMap<String,Variable>nm;
    private static HashMap<String,Instruction[]>lc=new HashMap<String,Instruction[]>();;
    private static HashMap<String,HashMap<String,Variable>>ll=new HashMap<String,HashMap<String,Variable>>();
    private Instruction[]c;
    public void run(String l){
        if(!this.nm.containsKey(l))return;
        Variable lbl=this.nm.get(l);
        if(lbl.t!=Variable.Type.LABEL)return;
        Label label=(Label)lbl;
        for(int rip=label.v;;rip++){
            Instruction i=this.c[rip];
            Command c=i.c;
            switch(c){
                case DEF:
                    String s=((Id)(i.o[0])).v;
                    Variable v=i.o[1];
                    if(v.t==Variable.Type.ID){
                        Id id=(Id)v;
                        if(!this.nm.containsKey(id.v)){
                            System.err.print(id.v+" not found\n");
                            System.exit(-1);
                        }
                        v=this.nm.get(id.v).copy();
                    }
                    this.nm.put(s,v);
                    break;
                case DS:
                    String ds=((Id)(i.o[0])).v;
                    if(!this.nm.containsKey(ds)){
                        System.err.print(ds+" not found\n");
                        System.exit(-1);
                    }
                    Variable dict=nm.get(ds);
                    if(dict.t!=Variable.Type.DICTIONARY){
                        System.err.print(ds+" is not a dictionary\n");
                        System.exit(-1);
                    }
                    Dictionary d=(Dictionary)dict;
                    Variable k=i.o[1];
                    if(k.t==Variable.Type.ID){
                        Id id=(Id)k;
                        if(!this.nm.containsKey(id.v)){
                            System.err.print(id.v+" not found\n");
                            System.exit(-1);
                        }
                        k=this.nm.get(id.v);
                    }
                    Variable val=i.o[1];
                    if(val.t==Variable.Type.ID){
                        Id id=(Id)val;
                        if(!this.nm.containsKey(id.v)){
                            System.err.print(id.v+" not found\n");
                            System.exit(-1);
                        }
                        val=this.nm.get(id.v).copy();
                    }
                    d.put(k,val);
                    break;
                case STOP:
                    return;
            }
        }
    }
    public Script(String p,Main m,HashMap<String,Variable>nm){
        nm=new HashMap<String,Variable>();
        if(lc.containsKey(p)){
            c=lc.get(p);
            HashMap<String,Variable>l=ll.get(p);
            for(String s:l.keySet())nm.put(s,l.get(s));
        }else{
            try{
                BufferedReader br=new BufferedReader(new FileReader(new File(m.abspth(p))));
                ArrayList<Instruction>al=new ArrayList<Instruction>();
                String l;
                while((l=br.readLine())!=null){
                    ArrayList<String>com=new ArrayList<String>();
                    int ll=l.length();
                    for(int i=0;i<ll;){
                        char chr=l.charAt(i);
                        while(Character.isWhitespace(chr)&&i<ll)chr=l.charAt(++i);
                        if(i>=ll)break;
                        String t="";
                        do{
                            t+=chr;
                            chr=l.charAt(++i);
                        }while(!Character.isWhitespace(chr));
                        com.add(t);
                    }
                    int cl=com.size();
                    int ol=cl-1;
                    Variable[]ops=new Variable[ol];
                    int rip=1;
                    for(int i=0;i<ol;i++){
                        String t=com.get(i+1);
                        Variable v=null;
                        if(t.charAt(0)=='"'){
                            if(t.charAt(t.length()-1)=='"'){
                                String n="";
                                for(int j=1;j<ol;j++)n+=t.charAt(j);
                                v=new Vstring(n);
                            }else{
                                System.err.println("Illegal token: "+t);
                                System.exit(-1);
                            }
                        }else{
                            try{
                                v=new Number(Double.parseDouble(t));
                            }catch(NumberFormatException __){
                                v=new Id(t);
                            }
                        }
                        ops[i]=v;
                    }
                    switch(com.get(0)){
                        case"def":
                            al.add(new Instruction(Command.DEF,ops));
                            rip++;
                            break;
                        case"ds":
                            al.add(new Instruction(Command.DS,ops));
                            rip++;
                            break;
                        case"label":
                            if(ol!=1){
                                System.err.println("Invalid number of arguments to label: "+ol);
                                System.exit(-1);
                            }
                            Variable lbl=ops[0];
                            if(lbl.t!=Variable.Type.ID){
                                System.err.print("Label may not be int or string\n");
                                System.exit(-1);
                            }
                            Id label=(Id)lbl;
                            String v=label.v;
                            if(this.nm.containsKey(v)){
                                System.err.println("Redefinition of label: "+v);
                                System.exit(-1);
                            }
                            this.nm.put(v,new Label(rip));
                            break;
                        case"stop":
                            al.add(new Instruction(Command.STOP,ops));
                            rip++;
                        case"#":
                            break;
                        default:
                            System.err.print("Command "+com.get(0)+" not found\n");
                            System.exit(-1);
                    }
                }
                this.c=al.toArray(new Instruction[0]);
            }catch(Exception e){
                System.err.println(e.getMessage());
                System.exit(-1);
            }
            lc.put(p,this.c);
            ll.put(p,this.nm);
        }
        this.nm=nm;
    }
}
