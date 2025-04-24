package org.finos.legend.legendql.model.metamodel.functions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * A function that calculates the modulo of two values.
 */
public class ModuloFunction implements Function {
    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitModuloFunction(this, parameter);
    }
}
