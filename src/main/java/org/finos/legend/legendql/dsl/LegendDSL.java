package org.finos.legend.legendql.dsl;

import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.schema.Database;
import org.finos.legend.legendql.model.schema.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * Main entry point for the LegendQL DSL.
 * Provides a fluent API for building queries in a JOOQ-style.
 */
public class LegendDSL {
    /**
     * Create a database with tables.
     *
     * @param databaseName the database name
     * @param tables       a map of table names to column definitions
     * @return an array of DSLTable instances, one for each table
     */
    public static DSLTable[] db(String databaseName, Map<String, Map<String, Class<?>>> tables) {
        Database database = new Database(databaseName);
        DSLTable[] result = new DSLTable[tables.size()];
        
        int i = 0;
        for (Map.Entry<String, Map<String, Class<?>>> entry : tables.entrySet()) {
            String tableName = entry.getKey();
            Map<String, Class<?>> columns = entry.getValue();
            
            database.addTable(tableName, columns);
            result[i++] = new DSLTable(database, database.getTable(tableName));
        }
        
        return result;
    }

    /**
     * Create a DSLTable instance for a table.
     *
     * @param databaseName the database name
     * @param tableName    the table name
     * @param columns      the column definitions
     * @return a new DSLTable instance
     */
    public static DSLTable table(String databaseName, String tableName, Map<String, Class<?>> columns) {
        Database database = new Database(databaseName);
        database.addTable(tableName, columns);
        return new DSLTable(database, database.getTable(tableName));
    }
    
    /**
     * Create a column reference.
     *
     * @param name the column name
     * @param type the column type
     * @return a new Column instance
     */
    public static <T> Column<T> column(String name, Class<T> type) {
        return new Column<>(name, type);
    }
    
    /**
     * Create a column reference with a table name.
     *
     * @param tableName the table name
     * @param name      the column name
     * @param type      the column type
     * @return a new Column instance
     */
    public static <T> Column<T> column(String tableName, String name, Class<T> type) {
        return new Column<>(tableName, name, type);
    }
    
    /**
     * Create a equals condition between two columns.
     *
     * @param left the left column
     * @param right the right column
     * @return an equals expression
     */
    public static Expression equal(Column<?> left, Column<?> right) {
        return left.eq(right);
    }
    
    /**
     * Create a equals condition between a column and a value.
     *
     * @param column the column
     * @param value the value
     * @return an equals expression
     */
    public static Expression equal(Column<?> column, Object value) {
        return column.eq(value);
    }
    
    /**
     * Create a greater than condition.
     *
     * @param column the column
     * @param value the value
     * @return a greater than expression
     */
    public static Expression greaterThan(Column<?> column, Object value) {
        return column.gt(value);
    }
    
    /**
     * Create a numeric column reference.
     *
     * @param name the column name
     * @param type the column type
     * @return a new NumericColumn instance
     */
    public static <T extends Number> NumericColumn<T> numericColumn(String name, Class<T> type) {
        return new NumericColumn<>(name, type);
    }
    
    /**
     * Create a numeric column reference with a table name.
     *
     * @param tableName the table name
     * @param name      the column name
     * @param type      the column type
     * @return a new NumericColumn instance
     */
    public static <T extends Number> NumericColumn<T> numericColumn(String tableName, String name, Class<T> type) {
        return new NumericColumn<>(tableName, name, type);
    }
}
