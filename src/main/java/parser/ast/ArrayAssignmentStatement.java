package parser.ast;

import lib.ArrayValue;
import lib.Value;
import lib.Variables;

public class ArrayAssignmentStatement implements Statement {
    private final String variable;
    private final Expression index;
    private final Expression value;

    public ArrayAssignmentStatement(String variable, Expression index, Expression value) {
        this.variable = variable;
        this.index = index;
        this.value = value;
    }

    @Override
    public void execute() {
        final Value var = Variables.get(variable);
        if (var instanceof ArrayValue) {
            final ArrayValue array = (ArrayValue) var;
            array.set((int)index.eval().asNumber(), value.eval());
        } else {
            throw new RuntimeException("Array expected");
        }
    }

    @Override
    public String toString() {
        return variable + "[" + index + "] = " + value;
    }
}
