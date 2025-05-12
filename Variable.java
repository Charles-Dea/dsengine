class Variable{
    public enum Type{
        NUMBER,
        STRING,
        DICTIONARY,
        LABEL,
        ID
    }
    public Type t;
    public Variable copy(){
        return null;
    }
    protected Variable(){}
}
