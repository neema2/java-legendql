package org.finos.legend.legendql.model.metamodel.clauses;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.schema.Table;

/**
 * A clause that joins two tables.
 */
public class JoinClause implements Clause {
    /**
     * The type of join.
     */
    public enum JoinType {
        INNER,
        LEFT,
        RIGHT,
        FULL
    }

    private final Table table;
    private final JoinType joinType;
    private final Expression condition;

    /**
     * Create a new join clause.
     *
     * @param table     the table to join with
     * @param joinType  the type of join
     * @param condition the join condition
     */
    public JoinClause(Table table, JoinType joinType, Expression condition) {
        this.table = table;
        this.joinType = joinType;
        this.condition = condition;
    }

    /**
     * Get the table to join with.
     *
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Get the type of join.
     *
     * @return the join type
     */
    public JoinType getJoinType() {
        return joinType;
    }

    /**
     * Get the join condition.
     *
     * @return the condition
     */
    public Expression getCondition() {
        return condition;
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitJoinClause(this, parameter);
    }
}
