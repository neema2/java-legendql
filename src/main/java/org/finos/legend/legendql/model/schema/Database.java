package org.finos.legend.legendql.model.schema;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a database in the LegendQL schema.
 */
public class Database {
    private final String name;
    private final Map<String, Table> tables = new HashMap<>();

    /**
     * Create a new database with the given name.
     *
     * @param name the database name
     */
    public Database(String name) {
        this.name = name;
    }

    /**
     * Get the database name.
     *
     * @return the database name
     */
    public String getName() {
        return name;
    }

    /**
     * Add a table to the database.
     *
     * @param name    the table name
     * @param columns the column definitions
     */
    public void addTable(String name, Map<String, Class<?>> columns) {
        tables.put(name, new Table(name, columns));
    }

    /**
     * Get a table by name.
     *
     * @param name the table name
     * @return the table
     */
    public Table getTable(String name) {
        Table table = tables.get(name);
        if (table == null) {
            throw new IllegalArgumentException("Table not found: " + name);
        }
        return table;
    }

    /**
     * Get all tables in the database.
     *
     * @return a map of table names to tables
     */
    public Map<String, Table> getTables() {
        return new HashMap<>(tables);
    }
}
