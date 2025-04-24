package org.finos.legend.legendql.model.metamodel.functions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * Represents a count function in the LegendQL metamodel.
 */
public class CountFunction implements Function {
    @Override
    public String getName() {
        return "count";
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitCountFunction(this, parameter);
    }
}
