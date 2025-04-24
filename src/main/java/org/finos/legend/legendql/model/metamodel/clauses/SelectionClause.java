package org.finos.legend.legendql.model.metamodel.clauses;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * A clause that selects columns from a table.
 */
public class SelectionClause implements Clause {
    private final List<Expression> expressions;

    /**
     * Create a new selection clause.
     *
     * @param expressions the expressions to select
     */
    public SelectionClause(List<Expression> expressions) {
        this.expressions = new ArrayList<>(expressions);
    }

    /**
     * Get the expressions to select.
     *
     * @return the expressions
     */
    public List<Expression> getExpressions() {
        return new ArrayList<>(expressions);
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitSelectionClause(this, parameter);
    }
}
