package org.finos.legend.legendql.dialect.purerelation;

import org.finos.legend.legendql.model.metamodel.Clause;
import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.runtime.Runtime;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A runtime that converts clauses to Legend Pure code.
 */
public abstract class PureRuntime implements Runtime {
    private final String name;

    public PureRuntime(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Convert the clauses to a string representation of Legend Pure code.
     *
     * @param clauses the clauses to convert
     * @return the string representation
     */
    public String executableToString(List<Clause> clauses) {
        PureRelationExpressionVisitor visitor = new PureRelationExpressionVisitor(this);
        String clausesString = clauses.stream()
                .map(clause -> clause.accept(visitor, ""))
                .collect(Collectors.joining("->"));
        return clausesString + this.accept(visitor, "");
    }

    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitRuntime(this, parameter);
    }
}
