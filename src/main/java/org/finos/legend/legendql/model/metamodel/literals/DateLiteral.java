package org.finos.legend.legendql.model.metamodel.literals;

import org.finos.legend.legendql.model.metamodel.ExecutionVisitor;
import org.finos.legend.legendql.model.metamodel.Literal;

import java.time.LocalDate;

/**
 * A literal that represents a date value.
 */
public class DateLiteral implements Literal<LocalDate> {
    private final LocalDate value;

    public DateLiteral(LocalDate value) {
        this.value = value;
    }

    @Override
    public LocalDate getValue() {
        return value;
    }

    @Override
    public <P, R> R accept(ExecutionVisitor<P, R> visitor, P parameter) {
        return visitor.visitDateLiteral(this, parameter);
    }
}
