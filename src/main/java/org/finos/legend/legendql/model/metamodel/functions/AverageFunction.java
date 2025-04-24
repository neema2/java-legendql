package org.finos.legend.legendql.model.metamodel.functions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * Represents an average function in the LegendQL metamodel.
 */
public class AverageFunction implements Function {
    @Override
    public String getName() {
        return "avg";
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitAverageFunction(this, parameter);
    }
}
