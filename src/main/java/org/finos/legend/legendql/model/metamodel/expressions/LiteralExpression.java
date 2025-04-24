package org.finos.legend.legendql.model.metamodel.expressions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.metamodel.Literal;

/**
 * An expression that represents a literal value.
 *
 * @param <T> the type of the literal value
 */
public class LiteralExpression<T> implements Expression {
    private final Literal<T> literal;

    public LiteralExpression(Literal<T> literal) {
        this.literal = literal;
    }

    public Literal<T> getLiteral() {
        return literal;
    }

    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitLiteralExpression(this, parameter);
    }
}
