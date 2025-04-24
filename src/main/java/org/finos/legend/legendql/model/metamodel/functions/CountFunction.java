package org.finos.legend.legendql.model.metamodel.functions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * A function that counts the number of rows.
 */
public class CountFunction implements Function {
    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitCountFunction(this, parameter);
    }
}
