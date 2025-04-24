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
    private Expression having;

    /**
     * Create a new group by clause.
     *
     * @param groupByExpressions the expressions to group by
     * @param aggregations       the aggregation expressions
     */
    public GroupByClause(List<Expression> groupByExpressions, Map<String, Expression> aggregations) {
        this.groupByExpressions = new ArrayList<>(groupByExpressions);
        this.aggregations = new HashMap<>(aggregations);
    }
    
    /**
     * Create a new group by clause with a having condition.
     *
     * @param groupByExpressions the expressions to group by
     * @param aggregations       the aggregation expressions
     * @param having             the having condition
     */
    public GroupByClause(List<Expression> groupByExpressions, Map<String, Expression> aggregations, Expression having) {
        this.groupByExpressions = new ArrayList<>(groupByExpressions);
        this.aggregations = new HashMap<>(aggregations);
        this.having = having;
    }

    /**
     * Get the expressions to group by.
     *
     * @return the expressions
     */
    public List<Expression> getGroupByExpressions() {
        return new ArrayList<>(groupByExpressions);
    }

    /**
     * Get the aggregation expressions.
     *
     * @return the aggregations
     */
    public Map<String, Expression> getAggregations() {
        return new HashMap<>(aggregations);
    }
    
    /**
     * Get the having condition.
     *
     * @return the having condition
     */
    public Expression getHaving() {
        return having;
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitGroupByClause(this, parameter);
    }
}
