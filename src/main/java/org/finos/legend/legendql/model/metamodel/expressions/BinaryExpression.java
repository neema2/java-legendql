package org.finos.legend.legendql.model.metamodel.expressions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.metamodel.operators.BinaryOperator;

/**
 * An expression that represents a binary operation.
 */
public class BinaryExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final BinaryOperator operator;

    public BinaryExpression(Expression left, BinaryOperator operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public BinaryOperator getOperator() {
        return operator;
    }

    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitBinaryExpression(this, parameter);
    }
}
