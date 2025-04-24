package org.finos.legend.legendql.model.metamodel.operators;

/**
 * Enumeration of unary operators in the LegendQL metamodel.
 */
public enum UnaryOperator {
    NOT("!"),
    NEGATE("-");
    
    private final String symbol;
    
    UnaryOperator(String symbol) {
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
