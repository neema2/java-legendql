package org.finos.legend.legendql.model.metamodel.runtime;

import org.finos.legend.legendql.model.metamodel.Clause;

import java.util.ArrayList;
import java.util.List;

/**
 * A data frame that represents the result of a query.
 *
 * @param <T> the type of the results
 */
public class DataFrame<T> {
    private final Runtime runtime;
    private final List<Clause> clauses;
    private T results;

    public DataFrame(Runtime runtime, List<Clause> clauses) {
        this.runtime = runtime;
        this.clauses = new ArrayList<>(clauses);
    }

    /**
     * Evaluate the clauses.
     *
     * @return this data frame
     */
    public DataFrame<T> eval() {
        this.results = runtime.eval(clauses);
        return this;
    }

    /**
     * Get the results.
     *
     * @return the results
     */
    public T data() {
        return results;
    }

    /**
     * Convert the clauses to a string representation.
     *
     * @return the string representation
     */
    public String executableToString() {
        return runtime.executableToString(clauses);
    }
}
