package org.finos.legend.legendql.model.metamodel.expressions;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Expression;

/**
 * An expression that represents a column reference.
 */
public class ColumnExpression implements Expression {
    private final String columnName;
    private final String tableName;

    public ColumnExpression(String columnName) {
        this(null, columnName);
    }

    public ColumnExpression(String tableName, String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getTableName() {
        return tableName;
    }

    public boolean hasTableName() {
        return tableName != null && !tableName.isEmpty();
    }

    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitColumnExpression(this, parameter);
    }
}
