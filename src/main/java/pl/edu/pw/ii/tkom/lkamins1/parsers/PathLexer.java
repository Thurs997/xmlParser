package pl.edu.pw.ii.tkom.lkamins1.parsers;

import pl.edu.pw.ii.tkom.lkamins1.exceptions.EndOfFileException;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.WrongValueTypeException;
import pl.edu.pw.ii.tkom.lkamins1.stateMachines.PathState;
import pl.edu.pw.ii.tkom.lkamins1.stateMachines.TokenState;
import pl.edu.pw.ii.tkom.lkamins1.tokens.PathToken;
import pl.edu.pw.ii.tkom.lkamins1.tokens.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;

/**
 * Created by lucas on 03.06.14.
 */
public class PathLexer {

    private Stack<PathToken> tokenStack;
    private Stack<Character> characterStack;
    private Stack<Character> temporaryStack;
    private BufferedReader reader;
    private PathToken builtToken;

    public void tokenize() {
        tokenStack = new Stack<PathToken>();
        characterStack = new Stack<Character>();
        PathToken token;
            while ((token = getNextToken()) != null) {
                tokenStack.push(token);
            }

    }

    public PathToken popToken () {return tokenStack.pop();}

    public void setPath(String path) {
        if(path.charAt(path.length()-1) != '/')
            path += '/';
        reader = new BufferedReader(new StringReader(path));
    }

    public boolean stackNotEmpty() {
        return !(tokenStack.isEmpty());
    }

    private PathToken getNextToken() throws IllegalStateException {
        PathState tokenState = PathState.INTIAL;
        temporaryStack = new Stack<Character>();
        try {
            while (!tokenState.isTerminal()) {
                tokenState = getNextStateAndFillTokenData(tokenState);
            }
        } catch (EndOfFileException e) {
            return null;
        }
        if(tokenState == PathState.ILLEGAL)
            throw new IllegalStateException();
        //logger.log(Level.INFO, "Built token.");
        return builtToken;
    }

    private PathState getNextStateAndFillTokenData(PathState actualState) throws EndOfFileException {
        char ch = getNextCharacter();
        //logger.log(Level.INFO, "Got character: " + ch);
        temporaryStack.push(ch);
        PathState nextState = actualState.nextState(ch);
        fillTokenData(nextState);
        return nextState;
    }

    private void fillTokenData(PathState tokenState) {
        switch(tokenState) {
            case PATH_INIT:            builtToken = new PathToken();
                                       temporaryStack.clear();
                                       break;
            case PATH_END:             characterStack.push(temporaryStack.pop());
                                       builtToken.pathOrAttribute = getStringFromStack();
                                       builtToken.type = PathToken.PathTokenType.PATH;
                                       break;
            case CONSTRAINT_INIT:      temporaryStack.pop();
                                       builtToken.pathOrAttribute = getStringFromStack();
                                       builtToken.type = PathToken.PathTokenType.CONSTRAINT;
                                       break;
            case CONSTRAINT_OPENQUOTE: temporaryStack.clear();
                                       break;
            case CONSTRAINT_CLOSEQUOTE:temporaryStack.pop();
                                       builtToken.constraintVal = getStringFromStack();
                                       break;
            case CONSTRAINT_END:       characterStack.push(temporaryStack.pop());
                                       break;
        }
    }


    private String getStringFromStack() {
        String retVal = "";
        while(!temporaryStack.isEmpty()) {
            retVal += temporaryStack.pop();
        }
        return new StringBuffer(retVal).reverse().toString();
    }

    private char getNextCharacter() throws EndOfFileException {
        if(characterStack.isEmpty()) {
            return getCharacterFromFile();
        } else {
            return characterStack.pop();
        }
    }

    private char getCharacterFromFile() throws EndOfFileException {
        int input = -1;
        try {
            input = reader.read();
        } catch (IOException e) {
            //impossible with string reader
        }
        if(input == -1) { //end of file
            throw new EndOfFileException();
        }
        return (char) input;
    }
}
