package org.finos.legend.legendql.model.metamodel.expressions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.metamodel.operators.UnaryOperator;

/**
 * An expression that represents a unary operation.
 */
public class UnaryExpression implements Expression {
    private final UnaryOperator operator;
    private final Expression expression;

    public UnaryExpression(UnaryOperator operator, Expression expression) {
        this.operator = operator;
        this.expression = expression;
    }

    public UnaryOperator getOperator() {
        return operator;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitUnaryExpression(this, parameter);
    }
}
