package org.finos.legend.legendql.examples;

import org.finos.legend.legendql.dsl.Column;
import org.finos.legend.legendql.dsl.DSLTable;
import org.finos.legend.legendql.dsl.LegendDSL;
import org.finos.legend.legendql.dsl.NumericColumn;
import org.finos.legend.legendql.model.metamodel.Expression;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Examples of using the LegendQL DSL in a JOOQ-style.
 * These examples correspond to the Python examples in the original LegendQL implementation.
 */
public class JooqStyleExample {

    public static void main(String[] args) {
        simpleFilterAndSelect();
        
        joinExample();
        
        groupByExample();
    }
    
    /**
     * Example 1: Simple filter and select
     * 
     * Python equivalent:
     * ```python
     * def example1():
     *     db = Database("mydb")
     *     db.add_table("person", {"name": str, "age": int})
     *     q = Query.from_table(db, db.get_table("person"))
     *     q.filter(lambda p: p.age > 30)
     *     q.select(lambda p: [p.name, p.age])
     *     return q
     * ```
     */
    public static void simpleFilterAndSelect() {
        Map<String, Class<?>> personColumns = new HashMap<>();
        personColumns.put("name", String.class);
        personColumns.put("age", Integer.class);
        
        DSLTable personTable = LegendDSL.table("mydb", "person", personColumns);
        
        personTable.filter(personTable.column("age").gt(30))
                  .select(personTable.column("name"), personTable.column("age"));
        
        System.out.println("Example 1: Simple filter and select");
        System.out.println("Filter: age > 30");
        System.out.println("Select: name, age");
        System.out.println();
    }
    
    /**
     * Example 2: Join example
     * 
     * Python equivalent:
     * ```python
     * def example2():
     *     db = Database("mydb")
     *     db.add_table("person", {"id": int, "name": str, "age": int, "firm_id": int})
     *     db.add_table("firm", {"id": int, "legal_name": str})
     *     q = Query.from_table(db, db.get_table("person"))
     *     q.join(db.get_table("firm"), lambda p, f: p.firm_id == f.id)
     *     q.select(lambda p, f: [p.name, f.legal_name])
     *     return q
     * ```
     */
    public static void joinExample() {
        Map<String, Class<?>> personColumns = new HashMap<>();
        personColumns.put("id", Integer.class);
        personColumns.put("name", String.class);
        personColumns.put("age", Integer.class);
        personColumns.put("firm_id", Integer.class);
        
        Map<String, Class<?>> firmColumns = new HashMap<>();
        firmColumns.put("id", Integer.class);
        firmColumns.put("legal_name", String.class);
        
        Map<String, Map<String, Class<?>>> tables = new HashMap<>();
        tables.put("person", personColumns);
        tables.put("firm", firmColumns);
        
        DSLTable[] dbTables = LegendDSL.db("mydb", tables);
        DSLTable personTable = dbTables[0];
        DSLTable firmTable = dbTables[1];
        
        Column<?> personFirmIdColumn = personTable.column("firm_id");
        Column<?> firmIdColumn = firmTable.column("id");
        
        personTable.join(firmTable, personFirmIdColumn.eq(firmIdColumn))
                  .select(personTable.column("name"), firmTable.column("legal_name"));
        
        System.out.println("Example 2: Join example");
        System.out.println("Join: person.firm_id = firm.id");
        System.out.println("Select: person.name, firm.legal_name");
        System.out.println();
    }
    
    /**
     * Example 3: Group by example
     * 
     * Python equivalent:
     * ```python
     * def example3():
     *     db = Database("mydb")
     *     db.add_table("trade", {"id": int, "trader_id": int, "quantity": int, "product": str})
     *     q = Query.from_table(db, db.get_table("trade"))
     *     q.group_by(lambda t: t.trader_id, {"total_quantity": lambda t: t.quantity.sum()})
     *     q.filter(lambda t: t.total_quantity > 1000)
     *     return q
     * ```
     */
    public static void groupByExample() {
        Map<String, Class<?>> tradeColumns = new HashMap<>();
        tradeColumns.put("id", Integer.class);
        tradeColumns.put("trader_id", Integer.class);
        tradeColumns.put("quantity", Integer.class);
        tradeColumns.put("product", String.class);
        
        DSLTable tradeTable = LegendDSL.table("mydb", "trade", tradeColumns);
        
        Column<?> traderIdColumn = tradeTable.column("trader_id");
        NumericColumn<Integer> quantityColumn = (NumericColumn<Integer>) tradeTable.column("quantity");
        
        Map<String, Expression> aggregations = new HashMap<>();
        aggregations.put("total_quantity", quantityColumn.sum());
        
        tradeTable.groupBy(Arrays.asList(traderIdColumn), aggregations)
                 .filter(tradeTable.column("total_quantity").gt(1000));
        
        System.out.println("Example 3: Group by example");
        System.out.println("Group by: trader_id");
        System.out.println("Aggregation: total_quantity = sum(quantity)");
        System.out.println("Having: total_quantity > 1000");
        System.out.println();
    }
}
