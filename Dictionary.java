import java.util.HashMap;
import java.util.ArrayList;
class Dictionary extends Variable{
    private HashMap<Double,Variable>nm;
    private HashMap<String,Variable>sm;
    private HashMap<Integer,Variable>lm;
    public Variable get(Variable k){
        switch(k.t){
            case Type.NUMBER:
                Number n=(Number)k;
                return nm.get(n.v);
            case Type.LABEL:
                Label l=(Label)k;
                return lm.get(l.v);
            case Type.STRING:
                Vstring s=(Vstring)k;
                return sm.get(s.v);
        }
        return null;
    }
    public void put(Variable k,Variable v){
        switch(k.t){
            case Type.NUMBER:
                Number n=(Number)k;
                nm.put(n.v,v);
                return;
            case Type.LABEL:
                Label l=(Label)k;
                lm.put(l.v,v);
                return;
            case Type.STRING:
                Vstring s=(Vstring)k;
                sm.put(s.v,v);
        }
    }
    public ArrayList<Variable>values(){
        ArrayList<Variable>r=new ArrayList<Variable>();
        for(Variable v:nm.values())r.add(v);
        for(Variable v:sm.values())r.add(v);
        return r;
    }
    @Override
    public Variable copy(){
        Dictionary nd=new Dictionary();
        for(Double k:this.nm.keySet())nd.put(new Number(k),this.nm.get(k).copy());
        for(String k:this.sm.keySet())nd.put(new Vstring(k),this.sm.get(k).copy());
        for(Integer k:this.lm.keySet())nd.put(new Label(k),this.lm.get(k).copy());
        return nd;
    }
    public Dictionary(){
        this.t=Type.DICTIONARY;
        this.nm=new HashMap<Double,Variable>();
        this.sm=new HashMap<String,Variable>();
        this.lm=new HashMap<Integer,Variable>();
    }
}
