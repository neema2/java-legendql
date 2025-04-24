package org.finos.legend.legendql.model.metamodel.literals;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Literal;

/**
 * A literal that represents a boolean value.
 */
public class BooleanLiteral implements Literal<Boolean> {
    private final Boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitBooleanLiteral(this, parameter);
    }
}
