package org.finos.legend.legendql.model.metamodel;

import org.finos.legend.legendql.model.metamodel.clauses.FilterClause;
import org.finos.legend.legendql.model.metamodel.clauses.GroupByClause;
import org.finos.legend.legendql.model.metamodel.clauses.JoinClause;
import org.finos.legend.legendql.model.metamodel.clauses.LimitClause;
import org.finos.legend.legendql.model.metamodel.clauses.OrderByClause;
import org.finos.legend.legendql.model.metamodel.clauses.SelectionClause;
import org.finos.legend.legendql.model.metamodel.expressions.BinaryExpression;
import org.finos.legend.legendql.model.metamodel.expressions.ColumnExpression;
import org.finos.legend.legendql.model.metamodel.expressions.LiteralExpression;
import org.finos.legend.legendql.model.metamodel.expressions.UnaryExpression;
import org.finos.legend.legendql.model.metamodel.functions.AverageFunction;
import org.finos.legend.legendql.model.metamodel.functions.CountFunction;

/**
 * Visitor interface for executing expressions and clauses in the LegendQL metamodel.
 *
 * @param <P> the parameter type
 * @param <T> the return type
 */
public interface ExecutionVisitor<P, T> {
    T visitFilterClause(FilterClause clause, P parameter);
    T visitGroupByClause(GroupByClause clause, P parameter);
    T visitJoinClause(JoinClause clause, P parameter);
    T visitLimitClause(LimitClause clause, P parameter);
    T visitOrderByClause(OrderByClause clause, P parameter);
    T visitSelectionClause(SelectionClause clause, P parameter);

    T visitBinaryExpression(BinaryExpression expression, P parameter);
    T visitColumnExpression(ColumnExpression expression, P parameter);
    T visitLiteralExpression(LiteralExpression<?> expression, P parameter);
    T visitUnaryExpression(UnaryExpression expression, P parameter);

    T visitAverageFunction(AverageFunction function, P parameter);
    T visitCountFunction(CountFunction function, P parameter);
}
