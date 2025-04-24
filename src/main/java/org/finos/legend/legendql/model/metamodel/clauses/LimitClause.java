package org.finos.legend.legendql.model.metamodel.clauses;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * A clause that limits the number of rows returned.
 */
public class LimitClause implements Clause {
    private final int limit;

    /**
     * Create a new limit clause.
     *
     * @param limit the maximum number of rows to return
     */
    public LimitClause(int limit) {
        this.limit = limit;
    }

    /**
     * Get the maximum number of rows to return.
     *
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitLimitClause(this, parameter);
    }
}
