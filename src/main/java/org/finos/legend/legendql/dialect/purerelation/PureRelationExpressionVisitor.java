package org.finos.legend.legendql.dialect.purerelation;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.clauses.*;
import org.finos.legend.legendql.model.metamodel.expressions.*;
import org.finos.legend.legendql.model.metamodel.functions.*;
import org.finos.legend.legendql.model.metamodel.literals.*;
import org.finos.legend.legendql.model.metamodel.operators.*;
import org.finos.legend.legendql.model.metamodel.runtime.Runtime;
import org.finos.legend.legendql.model.schema.Table;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A visitor that converts expressions and clauses to Legend Pure code.
 */
public class PureRelationExpressionVisitor implements ExecutionVisitor<String, String> {
    private final PureRuntime runtime;

    public PureRelationExpressionVisitor(PureRuntime runtime) {
        this.runtime = runtime;
    }

    @Override
    public String visitRuntime(Runtime runtime, String parameter) {
        return "->from(" + ((PureRuntime) runtime).getName() + ")";
    }

    @Override
    public String visitBinaryExpression(BinaryExpression expression, String parameter) {
        return expression.getLeft().accept(this, "") + 
               expression.getOperator().accept(this, "") + 
               expression.getRight().accept(this, "");
    }

    @Override
    public String visitColumnExpression(ColumnExpression expression, String parameter) {
        return expression.getName();
    }

    @Override
    public String visitLiteralExpression(LiteralExpression<?> expression, String parameter) {
        return expression.getLiteral().accept(this, "");
    }

    @Override
    public String visitUnaryExpression(UnaryExpression expression, String parameter) {
        return expression.getOperator().accept(this, "") + 
               expression.getExpression().accept(this, "");
    }

    @Override
    public String visitOperandExpression(OperandExpression expression, String parameter) {
        return expression.getExpression().accept(this, "");
    }

    @Override
    public String visitIntegerLiteral(IntegerLiteral literal, String parameter) {
        return String.valueOf(literal.getValue());
    }

    @Override
    public String visitStringLiteral(StringLiteral literal, String parameter) {
        return "'" + literal.getValue() + "'";
    }

    @Override
    public String visitBooleanLiteral(BooleanLiteral literal, String parameter) {
        return String.valueOf(literal.getValue());
    }

    @Override
    public String visitDateLiteral(DateLiteral literal, String parameter) {
        return "%" + literal.getValue().toString();
    }

    @Override
    public String visitCountFunction(CountFunction function, String parameter) {
        return "->count()";
    }

    @Override
    public String visitAverageFunction(AverageFunction function, String parameter) {
        return "->avg()";
    }

    @Override
    public String visitModuloFunction(ModuloFunction function, String parameter) {
        return "->mod(" + parameter + ")";
    }

    @Override
    public String visitExponentFunction(ExponentFunction function, String parameter) {
        return "->pow(" + parameter + ")";
    }

    @Override
    public String visitNotUnaryOperator(NotUnaryOperator operator, String parameter) {
        return "!";
    }

    @Override
    public String visitFilterClause(FilterClause clause, String parameter) {
        return "filter(" + clause.getExpression().accept(this, "") + ")";
    }

    @Override
    public String visitSelectionClause(SelectionClause clause, String parameter) {
        String selections = clause.getSelections().stream()
                .map(expr -> expr.accept(this, ""))
                .collect(Collectors.joining(", "));
        return "select(~[" + selections + "])";
    }

    @Override
    public String visitJoinClause(JoinClause clause, String parameter) {
        String joinType;
        switch (clause.getJoinType()) {
            case INNER:
                joinType = "JoinKind.INNER";
                break;
            case LEFT:
                joinType = "JoinKind.LEFT";
                break;
            case RIGHT:
                joinType = "JoinKind.RIGHT";
                break;
            case FULL:
                joinType = "JoinKind.FULL";
                break;
            default:
                joinType = "JoinKind.INNER";
        }
        
        Table rightTable = clause.getRightTable();
        String tableName = rightTable.getName();
        String databaseName = rightTable.getDatabase().getName();
        
        return "join(#{" + databaseName + "." + tableName + "}#, " + 
               joinType + ", {" + 
               clause.getCondition().accept(this, "") + "})";
    }

    @Override
    public String visitGroupByClause(GroupByClause clause, String parameter) {
        String groupByExpressions = clause.getGroupByExpressions().stream()
                .map(expr -> expr.accept(this, ""))
                .collect(Collectors.joining(", "));
        
        String aggregations = clause.getAggregations().entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue().accept(this, ""))
                .collect(Collectors.joining(", "));
        
        return "groupBy(~[" + groupByExpressions + "], ~[" + aggregations + "])";
    }

    @Override
    public String visitOrderByClause(OrderByClause clause, String parameter) {
        String orderByExpressions = clause.getOrderByExpressions().stream()
                .map(orderBy -> {
                    String direction = orderBy.getDirection() == OrderByClause.Direction.ASC ? 
                            "->ascending()" : "->descending()";
                    return "~" + orderBy.getExpression().accept(this, "") + direction;
                })
                .collect(Collectors.joining(", "));
        
        return "sort([" + orderByExpressions + "])";
    }

    @Override
    public String visitLimitClause(LimitClause clause, String parameter) {
        return "limit(" + clause.getLimit() + ")";
    }
}
