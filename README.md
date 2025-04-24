# Java LegendQL

This is a Java implementation of the LegendQL DSL, mirroring the functionality of the Python version at [hausea/legendql](https://github.com/hausea/legendql).

## Project Structure

The Java implementation follows a similar structure to the Python version:

- `org.finos.legend.legendql.model.schema`: Schema classes (Table, Database)
- `org.finos.legend.legendql.model.metamodel`: Metamodel interfaces and classes
- `org.finos.legend.legendql.model.metamodel.expressions`: Expression classes
- `org.finos.legend.legendql.model.metamodel.clauses`: Clause classes
- `org.finos.legend.legendql.model.metamodel.operators`: Operator classes
- `org.finos.legend.legendql.model.metamodel.visitors`: Visitor implementations
- `org.finos.legend.legendql.parser`: Lambda expression parser
- `org.finos.legend.legendql.examples`: Example usage of the DSL

## Core Classes

- `LegendQL`: Main entry point for the DSL with fluent API methods
- `Query`: Represents a query with clauses
- `ExecutionVisitor`: Visitor interface for processing expressions and clauses

## Usage Example

```java
// Create a database with tables
Map<String, Map<String, Class<?>>> tables = new HashMap<>();

// Define the Person table
Map<String, Class<?>> personColumns = new HashMap<>();
personColumns.put("id", Integer.class);
personColumns.put("name", String.class);
personColumns.put("age", Integer.class);
tables.put("Person", personColumns);

// Create LegendQL instances for each table
LegendQL[] legendQLs = LegendQL.db("ExampleDB", tables);
LegendQL personTable = legendQLs[0];

// Example: Simple query with filter
ColumnExpression ageColumn = LambdaParser.column("age");
personTable.filter(p -> LambdaParser.gt(ageColumn, LambdaParser.column("30")));
```

## Building

This project uses Maven for dependency management and building. To build the project:

```bash
mvn clean install
```

## Implementation Notes

Unlike the Python version which can directly parse lambda expressions using AST analysis, the Java implementation uses a more structured approach to building expressions. The `LambdaParser` class provides methods for creating expressions that can be used in the DSL methods.
