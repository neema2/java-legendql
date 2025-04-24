package org.finos.legend.legendql.model.metamodel.expressions;

import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.operators.UnaryOperator;

/**
 * Represents a unary expression in the LegendQL metamodel.
 */
public class UnaryExpression implements Expression {
    private final UnaryOperator operator;
    private final Expression operand;

    /**
     * Create a new unary expression.
     *
     * @param operator the unary operator
     * @param operand  the operand expression
     */
    public UnaryExpression(UnaryOperator operator, Expression operand) {
        this.operator = operator;
        this.operand = operand;
    }

    /**
     * Get the unary operator.
     *
     * @return the operator
     */
    public UnaryOperator getOperator() {
        return operator;
    }

    /**
     * Get the operand expression.
     *
     * @return the operand
     */
    public Expression getOperand() {
        return operand;
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitUnaryExpression(this, parameter);
    }
}
