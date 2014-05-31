package pl.edu.pw.ii.tkom.lkamins1.stateMachines;

import edu.tkom.lkamins1.tokens.Token;

/**
 * Created by lucas on 31.05.14.
 */
public enum ParserState {
    INITIAL {

    };

    public abstract ParserState nextState(Token tok);
    public abstract boolean isTerminal();
}
