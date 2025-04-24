package org.finos.legend.legendql.model.metamodel.expressions;

import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.operators.BinaryOperator;

/**
 * Represents a binary expression in the LegendQL metamodel.
 */
public class BinaryExpression implements Expression {
    private final Expression left;
    private final BinaryOperator operator;
    private final Expression right;

    /**
     * Create a new binary expression.
     *
     * @param left     the left expression
     * @param operator the binary operator
     * @param right    the right expression
     */
    public BinaryExpression(Expression left, BinaryOperator operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    /**
     * Get the left expression.
     *
     * @return the left expression
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Get the binary operator.
     *
     * @return the binary operator
     */
    public BinaryOperator getOperator() {
        return operator;
    }

    /**
     * Get the right expression.
     *
     * @return the right expression
     */
    public Expression getRight() {
        return right;
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitBinaryExpression(this, parameter);
    }
}
