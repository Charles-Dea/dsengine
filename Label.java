class Label extends Variable{
    public int v;
    public Variable copy(){
        return new Label(this.v);
    }
    public Label(int v){
        this.t=Type.LABEL;
        this.v=v;
    }
}
