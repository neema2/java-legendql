package org.finos.legend.legendql.model.metamodel.expressions;

import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;

/**
 * Represents a column reference expression in the LegendQL metamodel.
 */
public class ColumnExpression implements Expression {
    private final String tableName;
    private final String columnName;

    /**
     * Create a new column expression.
     *
     * @param tableName  the table name
     * @param columnName the column name
     */
    public ColumnExpression(String tableName, String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    /**
     * Get the table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Get the column name.
     *
     * @return the column name
     */
    public String getColumnName() {
        return columnName;
    }

    @Override
    public <P, T> T accept(ExecutionVisitor<P, T> visitor, P parameter) {
        return visitor.visitColumnExpression(this, parameter);
    }
}
