package org.finos.legend.legendql.model.metamodel.operators;

/**
 * Enumeration of binary operators in the LegendQL metamodel.
 */
public enum BinaryOperator {
    EQUALS("=="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL("<="),
    
    AND("&&"),
    OR("||"),
    
    ADD("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    MODULO("%");
    
    private final String symbol;
    
    BinaryOperator(String symbol) {
        this.symbol = symbol;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    @Override
    public String toString() {
        return symbol;
    }
}
