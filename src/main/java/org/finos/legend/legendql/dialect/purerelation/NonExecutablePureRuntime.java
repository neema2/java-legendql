package org.finos.legend.legendql.dialect.purerelation;

import org.finos.legend.legendql.model.metamodel.Clause;

import java.util.List;

/**
 * A pure runtime that does not support execution.
 */
public class NonExecutablePureRuntime extends PureRuntime {
    public NonExecutablePureRuntime(String name) {
        super(name);
    }

    @Override
    public <T> T eval(List<Clause> clauses) {
        throw new UnsupportedOperationException("This runtime does not support execution");
    }
}
