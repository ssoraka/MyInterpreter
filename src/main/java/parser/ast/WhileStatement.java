package parser.ast;

public class WhileStatement implements Statement {
    final private Expression condition;
    final private Statement statement;

    public WhileStatement(Expression condition, Statement statement) {
        this.statement = statement;
        this.condition = condition;
    }

    @Override
    public void execute() {
        while (condition.eval().asNumber() != 0.0) {
            try {
                statement.execute();
            } catch (BreakStatement bs) {
                break;
            } catch (ContinueStatement cs) {
                continue;
            }
        }
    }

    @Override
    public String toString() {
        return "while " + condition + "{ " +  statement + " }";
    }
}
