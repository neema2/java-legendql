package org.finos.legend.legendql.model.metamodel;

/**
 * Base interface for all expressions in the LegendQL metamodel.
 */
public interface Expression {
    /**
     * Accept a visitor for this expression.
     *
     * @param visitor   the visitor
     * @param parameter the parameter to pass to the visitor
     * @param <P>       the parameter type
     * @param <T>       the return type
     * @return the result of visiting this expression
     */
    <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter);
}
