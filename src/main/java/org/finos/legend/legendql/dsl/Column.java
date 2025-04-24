package org.finos.legend.legendql.dsl;

import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.metamodel.expressions.BinaryExpression;
import org.finos.legend.legendql.model.metamodel.expressions.ColumnExpression;
import org.finos.legend.legendql.model.metamodel.functions.AverageFunction;
import org.finos.legend.legendql.model.metamodel.functions.CountFunction;
import org.finos.legend.legendql.model.metamodel.literals.BooleanLiteral;
import org.finos.legend.legendql.model.metamodel.literals.IntegerLiteral;
import org.finos.legend.legendql.model.metamodel.literals.StringLiteral;
import org.finos.legend.legendql.model.metamodel.expressions.LiteralExpression;
import org.finos.legend.legendql.model.metamodel.operators.BinaryOperator;

/**
 * Represents a column in a table with methods for creating expressions.
 * This is the core of the JOOQ-style DSL for LegendQL.
 */
public class Column<T> {
    private final String name;
    private final String tableName;
    private final Class<T> type;

    public Column(String name, Class<T> type) {
        this(null, name, type);
    }

    public Column(String tableName, String name, Class<T> type) {
        this.tableName = tableName;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getTableName() {
        return tableName;
    }

    public Class<T> getType() {
        return type;
    }

    /**
     * Creates a column expression for this column.
     *
     * @return a column expression
     */
    public Expression asExpression() {
        return new ColumnExpression(tableName, name);
    }

    /**
     * Creates an equals expression.
     *
     * @param value the value to compare with
     * @return an equals expression
     */
    public Expression eq(Object value) {
        return new BinaryExpression(asExpression(), BinaryOperator.EQUALS, createLiteral(value));
    }

    /**
     * Creates a not equals expression.
     *
     * @param value the value to compare with
     * @return a not equals expression
     */
    public Expression ne(Object value) {
        return new BinaryExpression(asExpression(), BinaryOperator.NOT_EQUALS, createLiteral(value));
    }

    /**
     * Creates a greater than expression.
     *
     * @param value the value to compare with
     * @return a greater than expression
     */
    public Expression gt(Object value) {
        return new BinaryExpression(asExpression(), BinaryOperator.GREATER_THAN, createLiteral(value));
    }

    /**
     * Creates a greater than or equal expression.
     *
     * @param value the value to compare with
     * @return a greater than or equal expression
     */
    public Expression ge(Object value) {
        return new BinaryExpression(asExpression(), BinaryOperator.GREATER_THAN_OR_EQUAL, createLiteral(value));
    }

    /**
     * Creates a less than expression.
     *
     * @param value the value to compare with
     * @return a less than expression
     */
    public Expression lt(Object value) {
        return new BinaryExpression(asExpression(), BinaryOperator.LESS_THAN, createLiteral(value));
    }

    /**
     * Creates a less than or equal expression.
     *
     * @param value the value to compare with
     * @return a less than or equal expression
     */
    public Expression le(Object value) {
        return new BinaryExpression(asExpression(), BinaryOperator.LESS_THAN_OR_EQUAL, createLiteral(value));
    }

    /**
     * Creates an equals expression with another column.
     *
     * @param other the other column
     * @return an equals expression
     */
    public Expression eq(Column<?> other) {
        return new BinaryExpression(asExpression(), BinaryOperator.EQUALS, other.asExpression());
    }

    /**
     * Creates a not equals expression with another column.
     *
     * @param other the other column
     * @return a not equals expression
     */
    public Expression ne(Column<?> other) {
        return new BinaryExpression(asExpression(), BinaryOperator.NOT_EQUALS, other.asExpression());
    }

    /**
     * Creates a greater than expression with another column.
     *
     * @param other the other column
     * @return a greater than expression
     */
    public Expression gt(Column<?> other) {
        return new BinaryExpression(asExpression(), BinaryOperator.GREATER_THAN, other.asExpression());
    }

    /**
     * Creates a greater than or equal expression with another column.
     *
     * @param other the other column
     * @return a greater than or equal expression
     */
    public Expression ge(Column<?> other) {
        return new BinaryExpression(asExpression(), BinaryOperator.GREATER_THAN_OR_EQUAL, other.asExpression());
    }

    /**
     * Creates a less than expression with another column.
     *
     * @param other the other column
     * @return a less than expression
     */
    public Expression lt(Column<?> other) {
        return new BinaryExpression(asExpression(), BinaryOperator.LESS_THAN, other.asExpression());
    }

    /**
     * Creates a less than or equal expression with another column.
     *
     * @param other the other column
     * @return a less than or equal expression
     */
    public Expression le(Column<?> other) {
        return new BinaryExpression(asExpression(), BinaryOperator.LESS_THAN_OR_EQUAL, other.asExpression());
    }
    
    /**
     * Creates a count aggregation function.
     *
     * @return a count function expression
     */
    public Expression count() {
        return new CountFunction();
    }
    
    /**
     * Creates an average aggregation function.
     *
     * @return an average function expression
     */
    public Expression avg() {
        return new AverageFunction();
    }

    /**
     * Creates a literal expression from a value.
     *
     * @param value the value
     * @return a literal expression
     */
    private Expression createLiteral(Object value) {
        if (value instanceof Integer) {
            return new LiteralExpression<>(new IntegerLiteral((Integer) value));
        } else if (value instanceof String) {
            return new LiteralExpression<>(new StringLiteral((String) value));
        } else if (value instanceof Boolean) {
            return new LiteralExpression<>(new BooleanLiteral((Boolean) value));
        } else {
            throw new IllegalArgumentException("Unsupported literal type: " + value.getClass());
        }
    }
}
