package org.finos.legend.legendql.model.metamodel.clauses;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;

/**
 * A clause that filters rows based on a predicate.
 */
public class FilterClause implements Clause {
    private final Expression predicate;

    public FilterClause(Expression predicate) {
        this.predicate = predicate;
    }

    public Expression getPredicate() {
        return predicate;
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitFilterClause(this, parameter);
    }
}
