package org.finos.legend.legendql;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.schema.Database;
import org.finos.legend.legendql.model.schema.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a query in the LegendQL DSL.
 */
public class Query {
    private final Database database;
    private final Table table;
    private final List<Clause> clauses = new ArrayList<>();

    /**
     * Create a new query from a table.
     *
     * @param database the database
     * @param table    the table
     */
    private Query(Database database, Table table) {
        this.database = database;
        this.table = table;
    }

    /**
     * Create a new query from a table.
     *
     * @param database the database
     * @param table    the table
     * @return a new query
     */
    public static Query fromTable(Database database, Table table) {
        return new Query(database, table);
    }

    /**
     * Add a clause to the query.
     *
     * @param clause the clause to add
     */
    public void addClause(Clause clause) {
        clauses.add(clause);
    }

    /**
     * Get the database for this query.
     *
     * @return the database
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Get the table for this query.
     *
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Get the clauses for this query.
     *
     * @return the clauses
     */
    public List<Clause> getClauses() {
        return new ArrayList<>(clauses);
    }
}
