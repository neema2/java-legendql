package org.finos.legend.legendql.examples;

import org.finos.legend.legendql.LegendQL;
import org.finos.legend.legendql.Query;
import org.finos.legend.legendql.dialect.purerelation.NonExecutablePureRuntime;
import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.Expression;
import org.finos.legend.legendql.model.metamodel.expressions.BinaryExpression;
import org.finos.legend.legendql.model.metamodel.expressions.ColumnExpression;
import org.finos.legend.legendql.model.metamodel.expressions.LiteralExpression;
import org.finos.legend.legendql.model.metamodel.functions.AverageFunction;
import org.finos.legend.legendql.model.metamodel.functions.CountFunction;
import org.finos.legend.legendql.model.metamodel.literals.IntegerLiteral;
import org.finos.legend.legendql.model.metamodel.literals.StringLiteral;
import org.finos.legend.legendql.model.metamodel.operators.BinaryOperator;
import org.finos.legend.legendql.parser.LambdaParser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example usage of the LegendQL DSL.
 * This class demonstrates various features of the Java implementation of LegendQL,
 * inspired by the Python examples in legendql/examples.py.
 */
public class Example {
    public static void main(String[] args) {
        basicExample();
        
        fluentJoinExample();
        
        prqlExample();
        
        groupByExample();
        
        multiJoinExample();
        
        System.out.println("All examples executed successfully!");
    }
    
    /**
     * Basic example demonstrating simple filter, join, and select operations.
     */
    private static void basicExample() {
        System.out.println("\n=== Basic Example ===");
        
        Map<String, Map<String, Class<?>>> tables = new HashMap<>();
        
        Map<String, Class<?>> personColumns = new HashMap<>();
        personColumns.put("id", Integer.class);
        personColumns.put("name", String.class);
        personColumns.put("age", Integer.class);
        personColumns.put("address", String.class);
        tables.put("Person", personColumns);
        
        Map<String, Class<?>> orderColumns = new HashMap<>();
        orderColumns.put("id", Integer.class);
        orderColumns.put("personId", Integer.class);
        orderColumns.put("product", String.class);
        orderColumns.put("quantity", Integer.class);
        orderColumns.put("price", Double.class);
        tables.put("Order", orderColumns);
        
        LegendQL[] legendQLs = LegendQL.db("ExampleDB", tables);
        LegendQL personTable = legendQLs[0];
        LegendQL orderTable = legendQLs[1];
        
        ColumnExpression ageColumn = LambdaParser.column("age");
        personTable.filter(p -> LambdaParser.gt(ageColumn, LambdaParser.literal(30)));
        
        ColumnExpression personIdColumn = LambdaParser.column("Person", "id");
        ColumnExpression orderPersonIdColumn = LambdaParser.column("Order", "personId");
        personTable.join(orderTable, joinTables -> LambdaParser.eq(personIdColumn, orderPersonIdColumn));
        
        ColumnExpression nameColumn = LambdaParser.column("name");
        personTable.select(p -> new ColumnExpression[]{nameColumn, ageColumn});
        
        personTable.limit(10);
        
        printClauses(personTable.getQuery());
    }
    
    /**
     * Example with join using fluent-style API.
     * Inspired by the first example in examples.py.
     */
    private static void fluentJoinExample() {
        System.out.println("\n=== Fluent Join Example ===");
        
        Map<String, Map<String, Class<?>>> tables = new HashMap<>();
        
        Map<String, Class<?>> employeeColumns = new HashMap<>();
        employeeColumns.put("id", Integer.class);
        employeeColumns.put("name", String.class);
        employeeColumns.put("dept_id", String.class);
        employeeColumns.put("salary", Double.class);
        employeeColumns.put("title", String.class);
        tables.put("employees", employeeColumns);
        
        Map<String, Class<?>> departmentColumns = new HashMap<>();
        departmentColumns.put("id", Integer.class);
        departmentColumns.put("name", String.class);
        departmentColumns.put("city", String.class);
        departmentColumns.put("code", String.class);
        departmentColumns.put("location", String.class);
        tables.put("department", departmentColumns);
        
        LegendQL[] legendQLs = LegendQL.db("ExampleDB", tables);
        LegendQL empTable = legendQLs[0];
        LegendQL depTable = legendQLs[1];
        
        ColumnExpression empIdColumn = LambdaParser.column("id");
        empTable.filter(e -> LambdaParser.gt(empIdColumn, LambdaParser.literal(10)));
        
        ColumnExpression empDeptIdColumn = LambdaParser.column("dept_id");
        ColumnExpression depIdColumn = LambdaParser.column("department", "id");
        ColumnExpression depNameColumn = LambdaParser.column("department", "name");
        empTable.leftJoin(depTable, (e, d) -> {
            Expression joinCondition = LambdaParser.eq(empDeptIdColumn, depIdColumn);
            return joinCondition;
        });
        
        ColumnExpression idColumn = LambdaParser.column("id");
        Expression addExpression = new BinaryExpression(
                idColumn,
                BinaryOperator.ADD,
                empDeptIdColumn
        );
        empTable.extend(e -> new Expression[]{addExpression});
        
        ColumnExpression nameColumn = LambdaParser.column("name");
        ColumnExpression salaryColumn = LambdaParser.column("salary");
        
        Map<String, Expression> aggregations = new HashMap<>();
        aggregations.put("sum_salary", salaryColumn); // In a real implementation, this would use a SumFunction
        aggregations.put("count_dept", new CountFunction());
        
        empTable.groupBy(e -> new ColumnExpression[]{idColumn, nameColumn}, aggregations);
        
        empTable.filter(e -> LambdaParser.gt(idColumn, LambdaParser.literal(100)));
        
        printClauses(empTable.getQuery());
    }
    
    /**
     * PRQL example using reassignment for each expression.
     * Inspired by the PRQL example in examples.py.
     */
    private static void prqlExample() {
        System.out.println("\n=== PRQL Example ===");
        
        Map<String, Class<?>> employeeColumns = new HashMap<>();
        employeeColumns.put("id", Integer.class);
        employeeColumns.put("name", String.class);
        employeeColumns.put("title", String.class);
        employeeColumns.put("country", String.class);
        employeeColumns.put("dept_id", String.class);
        employeeColumns.put("salary", Double.class);
        employeeColumns.put("start_date", String.class);
        employeeColumns.put("benefits", Double.class);
        
        LegendQL empTable = LegendQL.table("ExampleDB", "employees", employeeColumns);
        
        ColumnExpression startDateColumn = LambdaParser.column("start_date");
        empTable.filter(e -> LambdaParser.gt(startDateColumn, LambdaParser.literal("2021-01-01")));
        
        ColumnExpression salaryColumn = LambdaParser.column("salary");
        ColumnExpression benefitsColumn = LambdaParser.column("benefits");
        
        Expression grossSalaryExpr = new BinaryExpression(
                salaryColumn,
                BinaryOperator.ADD,
                new LiteralExpression<>(new IntegerLiteral(10))
        );
        
        Expression grossCostExpr = new BinaryExpression(
                grossSalaryExpr,
                BinaryOperator.ADD,
                benefitsColumn
        );
        
        empTable.extend(e -> new Expression[]{grossSalaryExpr, grossCostExpr});
        
        empTable.filter(e -> LambdaParser.gt(grossCostExpr, LambdaParser.literal(0)));
        
        ColumnExpression titleColumn = LambdaParser.column("title");
        ColumnExpression countryColumn = LambdaParser.column("country");
        
        Map<String, Expression> aggregations = new HashMap<>();
        aggregations.put("avg_gross_salary", new AverageFunction());
        aggregations.put("sum_gross_cost", grossCostExpr); // In a real implementation, this would use a SumFunction
        
        empTable.groupBy(e -> new ColumnExpression[]{titleColumn, countryColumn}, aggregations);
        
        ColumnExpression sumGrossCostColumn = LambdaParser.column("sum_gross_cost");
        empTable.orderBy(e -> new Object[]{sumGrossCostColumn, countryColumn});
        
        empTable.limit(10);
        
        printClauses(empTable.getQuery());
    }
    
    /**
     * Example with group by and aggregation.
     * Inspired by the group by examples in examples.py.
     */
    private static void groupByExample() {
        System.out.println("\n=== Group By Example ===");
        
        Map<String, Class<?>> employeeColumns = new HashMap<>();
        employeeColumns.put("id", Integer.class);
        employeeColumns.put("name", String.class);
        employeeColumns.put("title", String.class);
        employeeColumns.put("dept_id", String.class);
        employeeColumns.put("salary", Double.class);
        
        LegendQL empTable = LegendQL.table("ExampleDB", "employees", employeeColumns);
        
        ColumnExpression titleColumn = LambdaParser.column("title");
        ColumnExpression salaryColumn = LambdaParser.column("salary");
        
        Map<String, Expression> aggregations = new HashMap<>();
        aggregations.put("avg_salary", new AverageFunction());
        
        empTable.groupBy(e -> new ColumnExpression[]{titleColumn}, aggregations);
        
        printClauses(empTable.getQuery());
        
        empTable = LegendQL.table("ExampleDB", "employees", employeeColumns);
        
        ColumnExpression deptIdColumn = LambdaParser.column("dept_id");
        
        empTable.groupBy(e -> new ColumnExpression[]{titleColumn, deptIdColumn}, aggregations);
        
        printClauses(empTable.getQuery());
    }
    
    /**
     * Example with multiple joins.
     * Inspired by the multiple join examples in examples.py.
     */
    private static void multiJoinExample() {
        System.out.println("\n=== Multiple Join Example ===");
        
        Map<String, Map<String, Class<?>>> tables = new HashMap<>();
        
        Map<String, Class<?>> employeeColumns = new HashMap<>();
        employeeColumns.put("id", Integer.class);
        employeeColumns.put("name", String.class);
        employeeColumns.put("dept_id", String.class);
        employeeColumns.put("salary", Double.class);
        employeeColumns.put("title", String.class);
        tables.put("employees", employeeColumns);
        
        Map<String, Class<?>> departmentColumns = new HashMap<>();
        departmentColumns.put("id", Integer.class);
        departmentColumns.put("name", String.class);
        departmentColumns.put("city", String.class);
        departmentColumns.put("code", String.class);
        departmentColumns.put("location", String.class);
        tables.put("department", departmentColumns);
        
        Map<String, Class<?>> locationColumns = new HashMap<>();
        locationColumns.put("id", Integer.class);
        locationColumns.put("name", String.class);
        locationColumns.put("country", String.class);
        locationColumns.put("code", String.class);
        tables.put("location", locationColumns);
        
        LegendQL[] legendQLs = LegendQL.db("ExampleDB", tables);
        LegendQL empTable = legendQLs[0];
        LegendQL depTable = legendQLs[1];
        LegendQL locTable = legendQLs[2];
        
        ColumnExpression empDeptIdColumn = LambdaParser.column("dept_id");
        ColumnExpression depIdColumn = LambdaParser.column("department", "id");
        empTable.leftJoin(depTable, (e, d) -> {
            Expression joinCondition = LambdaParser.eq(empDeptIdColumn, depIdColumn);
            return joinCondition;
        });
        
        ColumnExpression depCityColumn = LambdaParser.column("department", "city");
        ColumnExpression locIdColumn = LambdaParser.column("location", "id");
        empTable.leftJoin(locTable, (e, l) -> {
            Expression joinCondition = LambdaParser.eq(depCityColumn, locIdColumn);
            return joinCondition;
        });
        
        printClauses(empTable.getQuery());
    }
    
    /**
     * Helper method to print the clauses of a query.
     */
    private static void printClauses(Query query) {
        List<Clause> clauses = query.getClauses();
        System.out.println("Query clauses:");
        for (Clause clause : clauses) {
            System.out.println("  " + clause.getClass().getSimpleName());
        }
        
        NonExecutablePureRuntime runtime = new NonExecutablePureRuntime("ExampleRuntime");
        String pureCode = runtime.executableToString(clauses);
        System.out.println("Pure code: " + pureCode);
    }
}
