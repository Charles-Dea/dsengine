class Vstring extends Variable{
    public String v;
    @Override
    public Variable copy(){
        return new Vstring(this.v);
    }
    public Vstring(String v){
        this.t=Type.STRING;
        this.v=new String(v);
    }
}
