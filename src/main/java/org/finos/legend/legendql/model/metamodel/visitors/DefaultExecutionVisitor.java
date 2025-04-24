package org.finos.legend.legendql.model.metamodel.visitors;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.clauses.*;
import org.finos.legend.legendql.model.metamodel.expressions.*;
import org.finos.legend.legendql.model.metamodel.functions.*;
import org.finos.legend.legendql.model.metamodel.literals.*;
import org.finos.legend.legendql.model.metamodel.operators.*;
import org.finos.legend.legendql.model.metamodel.runtime.Runtime;

/**
 * Default implementation of the ExecutionVisitor interface.
 * This visitor provides default implementations for all visit methods.
 *
 * @param <P> the type of the parameter passed to the visitor
 * @param <T> the return type of the visitor
 */
public abstract class DefaultExecutionVisitor<P, T> implements ExecutionVisitor<P, T> {
    
    public T visitBinaryExpression(BinaryExpression expression, P parameter) {
        return null;
    }
    
    public T visitColumnExpression(ColumnExpression expression, P parameter) {
        return null;
    }
    
    public T visitLiteralExpression(LiteralExpression<?> expression, P parameter) {
        return null;
    }
    
    public T visitUnaryExpression(UnaryExpression expression, P parameter) {
        return null;
    }
    
    public T visitIntegerLiteral(IntegerLiteral literal, P parameter) {
        return null;
    }
    
    public T visitStringLiteral(StringLiteral literal, P parameter) {
        return null;
    }
    
    public T visitBooleanLiteral(BooleanLiteral literal, P parameter) {
        return null;
    }
    
    public T visitDateLiteral(DateLiteral literal, P parameter) {
        return null;
    }
    
    public T visitCountFunction(CountFunction function, P parameter) {
        return null;
    }
    
    public T visitAverageFunction(AverageFunction function, P parameter) {
        return null;
    }
    
    public T visitModuloFunction(ModuloFunction function, P parameter) {
        return null;
    }
    
    public T visitExponentFunction(ExponentFunction function, P parameter) {
        return null;
    }
    
    public T visitOperandExpression(OperandExpression expression, P parameter) {
        return null;
    }
    
    public T visitNotUnaryOperator(NotUnaryOperator operator, P parameter) {
        return null;
    }
    
    public T visitRuntime(Runtime runtime, P parameter) {
        return null;
    }
    
    public T visitFilterClause(FilterClause clause, P parameter) {
        return null;
    }
    
    public T visitSelectionClause(SelectionClause clause, P parameter) {
        return null;
    }
    
    public T visitJoinClause(JoinClause clause, P parameter) {
        return null;
    }
    
    public T visitGroupByClause(GroupByClause clause, P parameter) {
        return null;
    }
    
    public T visitOrderByClause(OrderByClause clause, P parameter) {
        return null;
    }
    
    public T visitLimitClause(LimitClause clause, P parameter) {
        return null;
    }
}
