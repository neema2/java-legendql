package org.finos.legend.legendql.model.schema;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a database table with columns and their types.
 */
public class Table {
    private final String tableName;
    private final Map<String, Class<?>> columns;

    public Table(String tableName, Map<String, Class<?>> columns) {
        this.tableName = tableName;
        this.columns = new HashMap<>(columns);
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, Class<?>> getColumns() {
        return new HashMap<>(columns);
    }

    public void addColumn(String columnName, Class<?> columnType) {
        columns.put(columnName, columnType);
    }

    public boolean validateColumn(String columnName) {
        return columns.containsKey(columnName);
    }

    public Table copy() {
        return new Table(tableName, new HashMap<>(columns));
    }
}
