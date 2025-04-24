# Java LegendQL

A Java implementation of the LegendQL DSL, inspired by the Python version at [hausea/legendql](https://github.com/hausea/legendql).

## Overview

This project implements a JOOQ-style DSL for building queries in Java. Unlike the Python version which uses lambdas, this implementation uses a fluent API with method chaining for better Java idiomaticity and type safety.

## Key Components

- **DSL Classes**: Core classes for building queries (DSLTable, Column, NumericColumn, LegendDSL)
- **Metamodel**: Classes representing the parsed DSL
- **Pure Code Generation**: Conversion from the metamodel to Legend Pure code

## Example Usage

```java
// Create a table with columns
Map<String, Class<?>> personColumns = new HashMap<>();
personColumns.put("id", Integer.class);
personColumns.put("name", String.class);
personColumns.put("age", Integer.class);

DSLTable personTable = LegendDSL.table("ExampleDB", "Person", personColumns);

// Build a query to filter people older than 30 and select their name and age
personTable.filter(personTable.column("age").gt(30))
          .select(personTable.column("name"), personTable.column("age"))
          .limit(10);

// Execute the query
System.out.println(personTable.getQuery().execute());
```

## Comparison with Python Version

The Python version uses lambdas for query building:

```python
from legendql import LegendQL

db = LegendQL()
Person = db.table("Person")

query = Person.filter(lambda p: p.age > 30).select(lambda p: [p.name, p.age]).limit(10)
```

While the Java version uses a more JOOQ-like approach:

```java
DSLTable personTable = LegendDSL.table("ExampleDB", "Person", personColumns);

personTable.filter(personTable.column("age").gt(30))
          .select(personTable.column("name"), personTable.column("age"))
          .limit(10);
```

This approach provides better type safety and IDE support in Java.
