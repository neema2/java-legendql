package org.finos.legend.legendql.model.metamodel.expressions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;

/**
 * An expression that represents an operand in a binary or unary expression.
 */
public class OperandExpression implements Expression {
    private final Expression expression;

    public OperandExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitOperandExpression(this, parameter);
    }
}
