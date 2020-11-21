package parser.ast;

import lib.Function;
import lib.Functions;

import java.util.List;

public class FunctionalDefine implements Statement {

    final private String name;
    final private List<String> argNames;
    final private Statement body;

    public FunctionalDefine(String name, List<String> argNames, Statement body) {
        this.name = name;
        this.argNames = argNames;
        this.body = body;
    }

    @Override
    public void execute() {
        Functions.set(name, new UserDefineFunction(argNames, body));
    }

    @Override
    public String toString() {
        return "def (" + argNames.toString() + " ) " + body.toString();
    }
}
