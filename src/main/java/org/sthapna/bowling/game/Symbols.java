package org.sthapna.bowling.game;

public enum Symbols {
    STRIKE("X"),
    SPARE("/"),
    PIN("-");

    private final String val;
    Symbols(String val){
        this.val = val;
    }
    
    public String val(){
        return val;
    }
    
}
