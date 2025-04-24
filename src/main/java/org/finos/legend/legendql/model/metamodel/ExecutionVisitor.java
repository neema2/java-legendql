package org.finos.legend.legendql.model.metamodel;

import org.finos.legend.legendql.model.metamodel.clauses.*;
import org.finos.legend.legendql.model.metamodel.expressions.*;
import org.finos.legend.legendql.model.metamodel.functions.*;
import org.finos.legend.legendql.model.metamodel.literals.*;
import org.finos.legend.legendql.model.metamodel.operators.*;
import org.finos.legend.legendql.model.metamodel.runtime.Runtime;

/**
 * Visitor interface for processing expressions and clauses.
 *
 * @param <P> the type of the parameter passed to the visitor
 * @param <T> the return type of the visitor
 */
public interface ExecutionVisitor<P, T> {
    T visitBinaryExpression(BinaryExpression expression, P parameter);
    T visitColumnExpression(ColumnExpression expression, P parameter);
    T visitLiteralExpression(LiteralExpression<?> expression, P parameter);
    T visitUnaryExpression(UnaryExpression expression, P parameter);
    T visitOperandExpression(OperandExpression expression, P parameter);
    
    T visitIntegerLiteral(IntegerLiteral literal, P parameter);
    T visitStringLiteral(StringLiteral literal, P parameter);
    T visitBooleanLiteral(BooleanLiteral literal, P parameter);
    T visitDateLiteral(DateLiteral literal, P parameter);
    
    T visitCountFunction(CountFunction function, P parameter);
    T visitAverageFunction(AverageFunction function, P parameter);
    T visitModuloFunction(ModuloFunction function, P parameter);
    T visitExponentFunction(ExponentFunction function, P parameter);
    
    T visitNotUnaryOperator(NotUnaryOperator operator, P parameter);
    
    T visitRuntime(Runtime runtime, P parameter);
    
    T visitFilterClause(FilterClause clause, P parameter);
    T visitSelectionClause(SelectionClause clause, P parameter);
    T visitJoinClause(JoinClause clause, P parameter);
    T visitGroupByClause(GroupByClause clause, P parameter);
    T visitOrderByClause(OrderByClause clause, P parameter);
    T visitLimitClause(LimitClause clause, P parameter);
}
