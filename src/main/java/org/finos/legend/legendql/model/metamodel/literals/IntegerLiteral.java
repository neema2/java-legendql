package org.finos.legend.legendql.model.metamodel.literals;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Literal;

/**
 * A literal that represents an integer value.
 */
public class IntegerLiteral implements Literal<Integer> {
    private final Integer value;

    public IntegerLiteral(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitIntegerLiteral(this, parameter);
    }
}
