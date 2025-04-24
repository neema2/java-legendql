package org.finos.legend.legendql.model.metamodel.clauses;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * A clause that limits the number of rows returned.
 */
public class LimitClause implements Clause {
    private final int limit;

    public LimitClause(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitLimitClause(this, parameter);
    }
}
