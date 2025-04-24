package org.finos.legend.legendql.model.metamodel.functions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * Interface for functions that can be used in expressions.
 */
public interface Function {
    /**
     * Accept a visitor.
     *
     * @param visitor   the visitor
     * @param parameter the parameter
     * @param <P>       the parameter type
     * @param <R>       the return type
     * @return the result of the visit
     */
    <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter);
}
