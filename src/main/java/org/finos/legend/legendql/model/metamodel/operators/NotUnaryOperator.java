package org.finos.legend.legendql.model.metamodel.operators;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * A unary operator that negates its operand.
 */
public class NotUnaryOperator implements UnaryOperator {
    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitNotUnaryOperator(this, parameter);
    }
}
