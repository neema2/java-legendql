package org.finos.legend.legendql.model.metamodel;

/**
 * Base interface for all expressions in the query model.
 */
public interface Expression {
    /**
     * Accept a visitor to process this expression.
     *
     * @param visitor   the visitor to accept
     * @param parameter the parameter to pass to the visitor
     * @param <P>       the type of the parameter
     * @param <T>       the return type of the visitor
     * @return the result of the visitor's visit method
     */
    <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter);
}
