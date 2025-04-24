package org.finos.legend.legendql.model.metamodel.clauses;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.schema.Table;

/**
 * A clause that joins two tables.
 */
public class JoinClause implements Clause {
    public enum JoinType {
        INNER,
        LEFT,
        RIGHT,
        FULL
    }

    private final Table rightTable;
    private final Expression condition;
    private final JoinType joinType;

    public JoinClause(Table rightTable, Expression condition, JoinType joinType) {
        this.rightTable = rightTable;
        this.condition = condition;
        this.joinType = joinType;
    }

    public Table getRightTable() {
        return rightTable;
    }

    public Expression getCondition() {
        return condition;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitJoinClause(this, parameter);
    }
}
