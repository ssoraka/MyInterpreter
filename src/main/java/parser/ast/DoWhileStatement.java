package parser.ast;

public class DoWhileStatement implements Statement {
    final private Expression condition;
    final private Statement statement;

    public DoWhileStatement(Expression condition, Statement statement) {
        this.statement = statement;
        this.condition = condition;
    }

    @Override
    public void execute() {
        do {
            try {
                statement.execute();
            } catch (BreakStatement bs) {
                break;
            } catch (ContinueStatement cs) {
                continue;
            }
        }
        while (condition.eval().asNumber() != 0.0);
    }

    @Override
    public String toString() {
        return "do { " +  statement + " } while " + condition;
    }
}
