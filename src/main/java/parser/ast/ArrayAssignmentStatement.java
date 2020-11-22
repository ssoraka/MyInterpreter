package parser.ast;

public class ArrayAssignmentStatement implements Statement {
    private final ArrayAccessExpression array;
    private final Expression value;

    public ArrayAssignmentStatement(ArrayAccessExpression array, Expression value) {
        this.array = array;
        this.value = value;
    }

    @Override
    public void execute() {
        array.getArray().set(array.lastIndex(), value.eval());
    }

    @Override
    public String toString() {
        return array.toString() + value.toString();
    }
}
