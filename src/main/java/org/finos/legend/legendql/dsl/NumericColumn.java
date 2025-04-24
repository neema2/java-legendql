package org.finos.legend.legendql.dsl;

import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.metamodel.expressions.BinaryExpression;
import org.finos.legend.legendql.model.metamodel.expressions.LiteralExpression;
import org.finos.legend.legendql.model.metamodel.literals.IntegerLiteral;
import org.finos.legend.legendql.model.metamodel.operators.BinaryOperator;

/**
 * Specialized column class for numeric values with additional operations.
 *
 * @param <T> the numeric type of the column
 */
public class NumericColumn<T extends Number> extends Column<T> {
    
    public NumericColumn(String name, Class<T> type) {
        super(name, type);
    }
    
    public NumericColumn(String tableName, String name, Class<T> type) {
        super(tableName, name, type);
    }
    
    /**
     * Creates an addition expression.
     *
     * @param value the value to add
     * @return an addition expression
     */
    public Expression add(Number value) {
        return new BinaryExpression(
                asExpression(),
                BinaryOperator.ADD,
                createNumericLiteral(value)
        );
    }
    
    /**
     * Creates an addition expression with another column.
     *
     * @param other the other column
     * @return an addition expression
     */
    public Expression add(NumericColumn<?> other) {
        return new BinaryExpression(
                asExpression(),
                BinaryOperator.ADD,
                other.asExpression()
        );
    }
    
    /**
     * Creates a subtraction expression.
     *
     * @param value the value to subtract
     * @return a subtraction expression
     */
    public Expression subtract(Number value) {
        return new BinaryExpression(
                asExpression(),
                BinaryOperator.MINUS,
                createNumericLiteral(value)
        );
    }
    
    /**
     * Creates a subtraction expression with another column.
     *
     * @param other the other column
     * @return a subtraction expression
     */
    public Expression subtract(NumericColumn<?> other) {
        return new BinaryExpression(
                asExpression(),
                BinaryOperator.MINUS,
                other.asExpression()
        );
    }
    
    /**
     * Creates a multiplication expression.
     *
     * @param value the value to multiply by
     * @return a multiplication expression
     */
    public Expression multiply(Number value) {
        return new BinaryExpression(
                asExpression(),
                BinaryOperator.MULTIPLY,
                createNumericLiteral(value)
        );
    }
    
    /**
     * Creates a multiplication expression with another column.
     *
     * @param other the other column
     * @return a multiplication expression
     */
    public Expression multiply(NumericColumn<?> other) {
        return new BinaryExpression(
                asExpression(),
                BinaryOperator.MULTIPLY,
                other.asExpression()
        );
    }
    
    /**
     * Creates a division expression.
     *
     * @param value the value to divide by
     * @return a division expression
     */
    public Expression divide(Number value) {
        return new BinaryExpression(
                asExpression(),
                BinaryOperator.DIVIDE,
                createNumericLiteral(value)
        );
    }
    
    /**
     * Creates a division expression with another column.
     *
     * @param other the other column
     * @return a division expression
     */
    public Expression divide(NumericColumn<?> other) {
        return new BinaryExpression(
                asExpression(),
                BinaryOperator.DIVIDE,
                other.asExpression()
        );
    }
    
    /**
     * Creates a literal expression from a numeric value.
     *
     * @param value the numeric value
     * @return a literal expression
     */
    private Expression createNumericLiteral(Number value) {
        if (value instanceof Integer) {
            return new LiteralExpression<>(new IntegerLiteral((Integer) value));
        } else {
            return new LiteralExpression<>(new IntegerLiteral(value.intValue()));
        }
    }
}
