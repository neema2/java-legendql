package org.finos.legend.legendql.model.metamodel.functions;

import org.finos.legend.legendql.model.metamodel.Expression;

/**
 * Base interface for all functions in the LegendQL metamodel.
 */
public interface Function extends Expression {
    /**
     * Get the name of the function.
     *
     * @return the function name
     */
    String getName();
}
