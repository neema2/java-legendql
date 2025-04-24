package org.finos.legend.legendql.model.metamodel.clauses;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * A clause that orders rows by expressions.
 */
public class OrderByClause implements Clause {
    /**
     * The direction of ordering.
     */
    public enum Direction {
        ASC,
        DESC
    }

    /**
     * An expression with an ordering direction.
     */
    public static class OrderByExpression {
        private final Expression expression;
        private final Direction direction;

        /**
         * Create a new order by expression.
         *
         * @param expression the expression to order by
         * @param direction  the direction of ordering
         */
        public OrderByExpression(Expression expression, Direction direction) {
            this.expression = expression;
            this.direction = direction;
        }

        /**
         * Get the expression to order by.
         *
         * @return the expression
         */
        public Expression getExpression() {
            return expression;
        }

        /**
         * Get the direction of ordering.
         *
         * @return the direction
         */
        public Direction getDirection() {
            return direction;
        }
    }

    private final List<OrderByExpression> expressions;

    /**
     * Create a new order by clause.
     *
     * @param expressions the expressions to order by
     */
    public OrderByClause(List<OrderByExpression> expressions) {
        this.expressions = new ArrayList<>(expressions);
    }

    /**
     * Get the expressions to order by.
     *
     * @return the expressions
     */
    public List<OrderByExpression> getExpressions() {
        return new ArrayList<>(expressions);
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitOrderByClause(this, parameter);
    }
}
