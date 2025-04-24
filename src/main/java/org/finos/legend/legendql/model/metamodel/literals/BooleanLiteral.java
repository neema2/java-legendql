package org.finos.legend.legendql.model.metamodel.literals;

import org.finos.legend.legendql.model.metamodel.Literal;

/**
 * Represents a boolean literal in the LegendQL metamodel.
 */
public class BooleanLiteral implements Literal<Boolean> {
    private final Boolean value;

    /**
     * Create a new boolean literal.
     *
     * @param value the boolean value
     */
    public BooleanLiteral(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
