package parser.ast;

import parser.ast.Expression;
import parser.ast.Statement;

public class ForStatement implements Statement {
    final private Statement initialization;
    final private Expression condition;
    final private Statement increment;
    final private Statement statement;

    public ForStatement(Statement initialization, Expression condition, Statement increment, Statement statement) {
        this.initialization = initialization;
        this.condition = condition;
        this.increment = increment;
        this.statement = statement;
    }

    @Override
    public void execute() {
        for (initialization.execute(); condition.eval().asNumber() != 0.0; increment.execute()) {
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
        return "for " + initialization + ", " +  condition + ", " + increment + " \n" + statement;
    }
}
