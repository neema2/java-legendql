package org.finos.legend.legendql.model.metamodel.literals;

import org.finos.legend.legendql.model.metamodel.Literal;

/**
 * Represents an integer literal in the LegendQL metamodel.
 */
public class IntegerLiteral implements Literal<Integer> {
    private final Integer value;

    /**
     * Create a new integer literal.
     *
     * @param value the integer value
     */
    public IntegerLiteral(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
