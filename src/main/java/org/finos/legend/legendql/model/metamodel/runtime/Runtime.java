package org.finos.legend.legendql.model.metamodel.runtime;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

import java.util.List;

/**
 * Interface for runtime execution of clauses.
 */
public interface Runtime {
    /**
     * Evaluate the clauses.
     *
     * @param clauses the clauses to evaluate
     * @param <T>     the return type
     * @return the result of the evaluation
     */
    <T> T eval(List<Clause> clauses);

    /**
     * Convert the clauses to a string representation.
     *
     * @param clauses the clauses to convert
     * @return the string representation
     */
    String executableToString(List<Clause> clauses);

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
