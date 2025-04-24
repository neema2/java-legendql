package org.finos.legend.legendql.dsl;

import org.finos.legend.legendql.Query;
import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.metamodel.clauses.FilterClause;
import org.finos.legend.legendql.model.metamodel.clauses.GroupByClause;
import org.finos.legend.legendql.model.metamodel.clauses.JoinClause;
import org.finos.legend.legendql.model.metamodel.clauses.LimitClause;
import org.finos.legend.legendql.model.metamodel.clauses.OrderByClause;
import org.finos.legend.legendql.model.metamodel.clauses.SelectionClause;
import org.finos.legend.legendql.model.schema.Database;
import org.finos.legend.legendql.model.schema.Table;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a table in the DSL with methods for building queries.
 * This is the main entry point for the JOOQ-style DSL for LegendQL.
 */
public class DSLTable {
    private final Query query;
    private final Map<String, Column<?>> columns = new HashMap<>();

    /**
     * Create a new DSLTable for a table.
     *
     * @param database the database
     * @param table    the table
     */
    public DSLTable(Database database, Table table) {
        this.query = Query.fromTable(database, table);
        
        for (Map.Entry<String, Class<?>> entry : table.getColumns().entrySet()) {
            String columnName = entry.getKey();
            Class<?> columnType = entry.getValue();
            columns.put(columnName, new Column<>(table.getName(), columnName, columnType));
        }
    }

    /**
     * Get a column by name.
     *
     * @param name the column name
     * @return the column
     */
    public Column<?> column(String name) {
        Column<?> column = columns.get(name);
        if (column == null) {
            throw new IllegalArgumentException("Column not found: " + name);
        }
        return column;
    }

    /**
     * Filter rows from the table.
     *
     * @param condition the filter condition
     * @return this DSLTable instance for method chaining
     */
    public DSLTable filter(Expression condition) {
        FilterClause filterClause = new FilterClause(condition);
        addClause(filterClause);
        return this;
    }

    /**
     * Select columns from the table.
     *
     * @param columns the columns to select
     * @return this DSLTable instance for method chaining
     */
    public DSLTable select(Column<?>... columns) {
        List<Expression> selectExpressions = Arrays.stream(columns)
                .map(Column::asExpression)
                .collect(Collectors.toList());
        SelectionClause selectionClause = new SelectionClause(selectExpressions);
        addClause(selectionClause);
        return this;
    }

    /**
     * Join this table with another table.
     *
     * @param other     the other table to join with
     * @param condition the join condition
     * @return this DSLTable instance for method chaining
     */
    public DSLTable join(DSLTable other, Expression condition) {
        JoinClause joinClause = new JoinClause(other.getQuery().getTable(), JoinClause.JoinType.INNER, condition);
        addClause(joinClause);
        return this;
    }

    /**
     * Left join this table with another table.
     *
     * @param other     the other table to join with
     * @param condition the join condition
     * @return this DSLTable instance for method chaining
     */
    public DSLTable leftJoin(DSLTable other, Expression condition) {
        JoinClause joinClause = new JoinClause(other.getQuery().getTable(), JoinClause.JoinType.LEFT, condition);
        addClause(joinClause);
        return this;
    }

    /**
     * Group rows by columns.
     *
     * @param groupByColumns the columns to group by
     * @param aggregations   a map of aggregation expressions
     * @return this DSLTable instance for method chaining
     */
    public DSLTable groupBy(List<Column<?>> groupByColumns, Map<String, Expression> aggregations) {
        List<Expression> groupByExpressions = groupByColumns.stream()
                .map(Column::asExpression)
                .collect(Collectors.toList());
        GroupByClause groupByClause = new GroupByClause(groupByExpressions, aggregations);
        addClause(groupByClause);
        return this;
    }

    /**
     * Group rows by columns with a having clause.
     *
     * @param groupByColumns the columns to group by
     * @param aggregations   a map of aggregation expressions
     * @param having         the having expression
     * @return this DSLTable instance for method chaining
     */
    public DSLTable groupBy(List<Column<?>> groupByColumns, Map<String, Expression> aggregations, Expression having) {
        List<Expression> groupByExpressions = groupByColumns.stream()
                .map(Column::asExpression)
                .collect(Collectors.toList());
        GroupByClause groupByClause = new GroupByClause(groupByExpressions, aggregations, having);
        addClause(groupByClause);
        return this;
    }

    /**
     * Order rows by columns.
     *
     * @param columns the columns to order by
     * @return this DSLTable instance for method chaining
     */
    public DSLTable orderBy(Column<?>... columns) {
        List<OrderByClause.OrderByExpression> orderByExpressions = Arrays.stream(columns)
                .map(column -> new OrderByClause.OrderByExpression(column.asExpression(), OrderByClause.Direction.ASC))
                .collect(Collectors.toList());
        OrderByClause orderByClause = new OrderByClause(orderByExpressions);
        addClause(orderByClause);
        return this;
    }

    /**
     * Limit the number of rows.
     *
     * @param limit the maximum number of rows to return
     * @return this DSLTable instance for method chaining
     */
    public DSLTable limit(int limit) {
        LimitClause limitClause = new LimitClause(limit);
        addClause(limitClause);
        return this;
    }

    /**
     * Get the query for this DSLTable instance.
     *
     * @return the query
     */
    public Query getQuery() {
        return query;
    }

    /**
     * Add a clause to the query.
     *
     * @param clause the clause to add
     */
    private void addClause(Clause clause) {
        query.addClause(clause);
    }
}
