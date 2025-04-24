package org.finos.legend.legendql.model.metamodel.functions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * A function that calculates the average of a column.
 */
public class AverageFunction implements Function {
    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitAverageFunction(this, parameter);
    }
}
