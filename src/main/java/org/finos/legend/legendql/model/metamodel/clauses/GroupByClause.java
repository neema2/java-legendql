package org.finos.legend.legendql.model.metamodel.clauses;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * A clause that groups rows by columns and applies aggregations.
 */
public class GroupByClause implements Clause {
    private final List<Expression> groupByExpressions;
    private final Map<String, Expression> aggregations;

    public GroupByClause(List<Expression> groupByExpressions, Map<String, Expression> aggregations) {
        this.groupByExpressions = new ArrayList<>(groupByExpressions);
        this.aggregations = new HashMap<>(aggregations);
    }

    public List<Expression> getGroupByExpressions() {
        return new ArrayList<>(groupByExpressions);
    }

    public Map<String, Expression> getAggregations() {
        return new HashMap<>(aggregations);
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitGroupByClause(this, parameter);
    }
}
