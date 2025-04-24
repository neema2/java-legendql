package org.finos.legend.legendql.model.metamodel.operators;

/**
 * Enum representing binary operators.
 */
public enum BinaryOperator {
    EQUALS("=="),
    NOT_EQUALS("!="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUALS("<="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUALS(">="),
    AND("&&"),
    OR("||"),
    PLUS("+"),
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

    /**
     * Get the operator for a symbol.
     *
     * @param symbol the symbol
     * @return the operator
     * @throws IllegalArgumentException if the symbol is not a valid operator
     */
    public static BinaryOperator fromSymbol(String symbol) {
        for (BinaryOperator operator : values()) {
            if (operator.getSymbol().equals(symbol)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + symbol);
    }
}
