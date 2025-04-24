package org.finos.legend.legendql.model.metamodel;

/**
 * Base interface for all clauses in the LegendQL metamodel.
 */
public interface Clause {
    /**
     * Accept a visitor for this clause.
     *
     * @param visitor   the visitor
     * @param parameter the parameter to pass to the visitor
     * @param <P>       the parameter type
     * @param <T>       the return type
     * @return the result of visiting this clause
     */
    <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter);
}
