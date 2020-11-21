package parser.ast;

import lib.Function;
import lib.Functions;
import lib.NumberValue;
import lib.Value;

import java.util.List;

public class UserDefineFunction implements Function {

    final private List<String> argNames;
    final private Statement body;

    public UserDefineFunction(List<String> argNames, Statement body) {
        this.argNames = argNames;
        this.body = body;
    }

    public int getArgSize() {
        return argNames.size();
    }

    public String getArgName(int index) {
        if (index < 0 || index >= getArgSize()) return "";
        return argNames.get(index);
    }

    @Override
    public Value execute(Value... args) {
        try {
            body.execute();
            return NumberValue.ZERO;
        } catch (ReturnStatement rt) {
            return rt.getResult();
        }
    }
}
