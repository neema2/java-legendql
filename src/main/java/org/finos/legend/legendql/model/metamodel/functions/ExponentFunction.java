package org.finos.legend.legendql.model.metamodel.functions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * A function that calculates the exponent of a value.
 */
public class ExponentFunction implements Function {
    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitExponentFunction(this, parameter);
    }
}
