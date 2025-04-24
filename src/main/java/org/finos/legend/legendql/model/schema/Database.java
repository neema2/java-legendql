package org.finos.legend.legendql.model.schema;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a database with tables.
 */
public class Database {
    private final String name;
    private final Map<String, Table> tables;

    public Database(String name) {
        this.name = name;
        this.tables = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    public void addTable(String tableName, Map<String, Class<?>> columns) {
        tables.put(tableName, new Table(tableName, columns));
    }

    public Map<String, Table> getTables() {
        return new HashMap<>(tables);
    }
}
