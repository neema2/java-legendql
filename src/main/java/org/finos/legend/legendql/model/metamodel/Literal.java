package org.finos.legend.legendql.model.metamodel;

/**
 * Base interface for all literal values in expressions.
 *
 * @param <T> the type of the literal value
 */
public interface Literal<T> {
    /**
     * Get the value of the literal.
     *
     * @return the value
     */
    T getValue();

    /**
     * Accept a visitor to process this literal.
     *
     * @param visitor   the visitor to accept
     * @param parameter the parameter to pass to the visitor
     * @param <P>       the type of the parameter
     * @param <R>       the return type of the visitor
     * @return the result of the visitor's visit method
     */
    <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter);
}
