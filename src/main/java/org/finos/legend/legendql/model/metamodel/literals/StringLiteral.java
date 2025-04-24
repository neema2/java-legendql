package org.finos.legend.legendql.model.metamodel.literals;

import org.finos.legend.legendql.model.metamodel.Literal;

/**
 * Represents a string literal in the LegendQL metamodel.
 */
public class StringLiteral implements Literal<String> {
    private final String value;

    /**
     * Create a new string literal.
     *
     * @param value the string value
     */
    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
