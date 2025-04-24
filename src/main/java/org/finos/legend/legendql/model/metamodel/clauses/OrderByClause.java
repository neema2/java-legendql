package org.finos.legend.legendql.model.metamodel.clauses;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * A clause that orders rows by columns.
 */
public class OrderByClause implements Clause {
    public enum Direction {
        ASC,
        DESC
    }

    public static class OrderByExpression {
        private final Expression expression;
        private final Direction direction;

        public OrderByExpression(Expression expression, Direction direction) {
            this.expression = expression;
            this.direction = direction;
        }

        public Expression getExpression() {
            return expression;
        }

        public Direction getDirection() {
            return direction;
        }
    }

    private final List<OrderByExpression> orderByExpressions;

    public OrderByClause(List<OrderByExpression> orderByExpressions) {
        this.orderByExpressions = new ArrayList<>(orderByExpressions);
    }

    public List<OrderByExpression> getOrderByExpressions() {
        return new ArrayList<>(orderByExpressions);
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitOrderByClause(this, parameter);
    }
}
