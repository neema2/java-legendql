package org.finos.legend.legendql.model.metamodel.clauses;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;

/**
 * A clause that filters rows based on a condition.
 */
public class FilterClause implements Clause {
    private final Expression condition;

    /**
     * Create a new filter clause.
     *
     * @param condition the filter condition
     */
    public FilterClause(Expression condition) {
        this.condition = condition;
    }

    /**
     * Get the filter condition.
     *
     * @return the filter condition
     */
    public Expression getCondition() {
        return condition;
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitFilterClause(this, parameter);
    }
}
