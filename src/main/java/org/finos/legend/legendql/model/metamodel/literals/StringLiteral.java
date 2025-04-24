package org.finos.legend.legendql.model.metamodel.literals;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Literal;

/**
 * A literal that represents a string value.
 */
public class StringLiteral implements Literal<String> {
    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitStringLiteral(this, parameter);
    }
}
