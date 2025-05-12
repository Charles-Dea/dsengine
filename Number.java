class Number extends Variable{
    public double v;
    @Override
    public Variable copy(){
        return new Number(this.v);
    }
    public Number(double v){
        this.t=Type.NUMBER;
        this.v=v;
    }
}
