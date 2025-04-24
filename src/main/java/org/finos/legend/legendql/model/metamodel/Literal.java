package org.finos.legend.legendql.model.metamodel;

/**
 * Base interface for all literals in the LegendQL metamodel.
 *
 * @param <T> the type of the literal value
 */
public interface Literal<T> {
    /**
     * Get the value of the literal.
     *
     * @return the value
     */
    T getValue();
}
