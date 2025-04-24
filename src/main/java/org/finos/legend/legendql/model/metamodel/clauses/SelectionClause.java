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
    private final List<Expression> selections;

    public SelectionClause(List<Expression> selections) {
        this.selections = new ArrayList<>(selections);
    }

    public List<Expression> getSelections() {
        return new ArrayList<>(selections);
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitSelectionClause(this, parameter);
    }
}
