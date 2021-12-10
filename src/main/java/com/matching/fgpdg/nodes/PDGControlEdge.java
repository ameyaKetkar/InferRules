package com.matching.fgpdg.nodes;


public class PDGControlEdge extends PDGEdge {
    protected String label;

    public PDGControlEdge(PDGNode point, PDGNode next, String label) {
        super(point, next);
        this.label = label;
        this.source.addOutEdge(this);
        this.target.addInEdge(this);
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getExasLabel() {
        return "_control_";
    }

    @Override
    public String toString() {
        return getLabel();
    }

    @Override
    public  boolean isEqualEdge(PDGEdge node){
        return node instanceof PDGControlEdge && getLabel().equals(node.getLabel());
    }
}