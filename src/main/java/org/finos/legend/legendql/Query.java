package org.finos.legend.legendql;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.schema.Database;
import org.finos.legend.legendql.model.schema.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a query with clauses.
 */
public class Query {
    private final Database database;
    private Table table;
    private final List<Clause> clauses;

    private Query(Database database, Table table) {
        this.database = database;
        this.table = table;
        this.clauses = new ArrayList<>();
    }

    /**
     * Create a query from a table.
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
     * Update the table for the query.
     *
     * @param table the new table
     */
    public void updateTable(Table table) {
        this.table = table;
    }

    /**
     * Get the database for the query.
     *
     * @return the database
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Get the table for the query.
     *
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Get the clauses for the query.
     *
     * @return the clauses
     */
    public List<Clause> getClauses() {
        return new ArrayList<>(clauses);
    }
}
