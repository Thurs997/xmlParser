package pl.edu.pw.ii.tkom.lkamins1;

import pl.edu.pw.ii.tkom.lkamins1.exceptions.EndOfFileException;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.WrongValueTypeException;
import pl.edu.pw.ii.tkom.lkamins1.stateMachines.TokenState;
import pl.edu.pw.ii.tkom.lkamins1.tokens.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Stack;

/**
 * Created by lucas on 11.05.14.
 */
class Lexer {
    BufferedReader reader;
    Stack<Character> characterStack;
    Stack<Character> temporaryStack;
    Token builtToken;

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
        return builtToken;
    }

    private TokenState getNextStateAndFillTokenData(TokenState actualState) throws IOException, EndOfFileException, WrongValueTypeException {
        char ch = getNextCharacter();
        temporaryStack.push(ch);
        TokenState nextState = actualState.nextState(ch);
        fillTokenData(nextState);
        return nextState;
    }

    private void fillTokenData(TokenState state) throws WrongValueTypeException {
        switch (state) {
            case TAG_COMMENT_OPEN_EXCLAMATION: builtToken = new CommentTagToken();
                                               break;
            case TAG_OPEN_INIT               : builtToken = new OpeningTagToken();
                                               temporaryStack.clear();
                                               break;
            case TAG_OPEN_WHITESPACE         : temporaryStack.pop();
                                               ((OpeningTagToken)builtToken).setTag(getStringFromStack());
                                               break;
            case TAG_OPEN_TYPE_OPENQUOTE     : temporaryStack.clear();
                                               break;
            case TAG_OPEN_TYPE_CLOSEQUOTE    : temporaryStack.pop();
                                               ((OpeningTagToken)builtToken).setType(getStringFromStack());
            case TAG_CLOSE_INIT              : builtToken = new ClosingTagToken();
                                               temporaryStack.clear();
                                               break;
            case TAG_CLOSE_END               : temporaryStack.pop();
                                               ((ClosingTagToken)builtToken).setTag(getStringFromStack());
                                               break;
            case VALUE_END                   : characterStack.push(temporaryStack.pop());
                                               builtToken = new ValueToken(getStringFromStack());
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
