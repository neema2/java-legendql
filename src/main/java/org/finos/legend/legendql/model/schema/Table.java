package org.finos.legend.legendql.model.schema;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a table in the LegendQL schema.
 */
public class Table {
    private final String name;
    private final Map<String, Class<?>> columns;

    /**
     * Create a new table with the given name and columns.
     *
     * @param name    the table name
     * @param columns the column definitions
     */
    public Table(String name, Map<String, Class<?>> columns) {
        this.name = name;
        this.columns = new HashMap<>(columns);
    }

    /**
     * Get the table name.
     *
     * @return the table name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the column definitions.
     *
     * @return a map of column names to column types
     */
    public Map<String, Class<?>> getColumns() {
        return new HashMap<>(columns);
    }

    /**
     * Get a column type by name.
     *
     * @param name the column name
     * @return the column type
     */
    public Class<?> getColumnType(String name) {
        Class<?> type = columns.get(name);
        if (type == null) {
            throw new IllegalArgumentException("Column not found: " + name);
        }
        return type;
    }
}
