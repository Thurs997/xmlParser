package pl.edu.pw.ii.tkom.lkamins1.parsers;

import pl.edu.pw.ii.tkom.lkamins1.tokens.Value;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.EndOfFileException;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.WrongValueTypeException;
import pl.edu.pw.ii.tkom.lkamins1.stateMachines.TokenState;
import pl.edu.pw.ii.tkom.lkamins1.tokens.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * Created by lucas on 11.05.14.
 */
public class Lexer {
    BufferedReader reader;
    Stack<Character> characterStack;
    Stack<Character> temporaryStack;
    Token builtToken;
    private static final Logger logger = Logger.getLogger(Lexer.class.getName());
    public Lexer() {
        characterStack = new Stack<Character>();
    }

    public void openFile(String fileName) throws FileNotFoundException {
        reader = new BufferedReader (
                new InputStreamReader(
                        new FileInputStream(fileName),
                        Charset.forName("UTF-8")
                )
        );
    }

    public void setReader(BufferedReader reader) {this.reader = reader;}

    public void closeFile() throws IOException {
        reader.close();
    }

    public Token getNextToken() throws IllegalStateException, IOException, EndOfFileException, WrongValueTypeException {
        TokenState tokenState = TokenState.INITIAL;
        temporaryStack = new Stack<Character>();
        while(!tokenState.isTerminal()) {
            tokenState = getNextStateAndFillTokenData(tokenState);
        }
        if(tokenState == TokenState.ILLEGAL)
            throw new IllegalStateException();
        //logger.log(Level.INFO, "Built token.");
        if(builtToken.type == Token.TokenType.COMMENT)
            return getNextToken();
        else
            return builtToken;
    }

    private TokenState getNextStateAndFillTokenData(TokenState actualState) throws IOException, EndOfFileException, WrongValueTypeException {
        char ch = getNextCharacter();
        //logger.log(Level.INFO, "Got character: " + ch);
        temporaryStack.push(ch);
        TokenState nextState = actualState.nextState(ch);
        fillTokenData(nextState);
        return nextState;
    }

    private void fillTokenData(TokenState state) throws WrongValueTypeException {
        //logger.log(Level.INFO, "State " + state.toString());
        switch (state) {
            case TAG_COMMENT_OPEN_EXCLAMATION: builtToken = new Token(Token.TokenType.COMMENT);
                                               break;
            case TAG_INIT                    : temporaryStack.clear();
                                               break;
            case TAG_OPEN_INIT               : builtToken = new Token(Token.TokenType.OPENING);
                                               break;
            case TAG_OPEN_WHITESPACE         : temporaryStack.pop();
                                               builtToken.setTag(getStringFromStack());
                                               break;
            case TAG_OPEN_TYPE_OPENQUOTE     : temporaryStack.clear();
                                               break;
            case TAG_OPEN_TYPE_CLOSEQUOTE    : temporaryStack.pop();
                                               builtToken.setValueType(getStringFromStack());
                                               break;
            case TAG_CLOSE_INIT              : builtToken = new Token(Token.TokenType.CLOSING);
                                               temporaryStack.clear();
                                               break;
            case TAG_CLOSE_END               : temporaryStack.pop();
                                               builtToken.setTag(getStringFromStack());
                                               break;
            case VALUE_END                   : characterStack.push(temporaryStack.pop());
                                               builtToken = new Token(Token.TokenType.VALUE);
                                               builtToken.setValue(new Value(getStringFromStack()));
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

    private char getNextCharacter() throws EndOfFileException, IOException {
        if(characterStack.isEmpty()) {
            return getCharacterFromFile();
        } else {
            return characterStack.pop();
        }
    }

    private char getCharacterFromFile() throws EndOfFileException, IOException {
        int input;
        input = reader.read();
        if(input == -1) { //end of file
            throw new EndOfFileException();
        }
        return (char) input;
    }


}
