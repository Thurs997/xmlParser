package pl.edu.pw.ii.tkom.lkamins1.parsers;

import pl.edu.pw.ii.tkom.lkamins1.stateMachines.ValueState;

/**
 * Created by lucas on 16.05.14.
 */
public class ValueParser {
    public static int parseToInt(String stringToParse) throws IllegalStateException {
        ValueState state = ValueState.INT_INITIAL;
        int retVal = 0;
        boolean negative = false;
        for(int i = 0; i < stringToParse.length(); ++i) {
            char c = stringToParse.charAt(i);
            state = state.nextState(c);
            if(state == ValueState.INT_DIGIT) {
                retVal *= 10;
                retVal += Character.getNumericValue(c);
            } else if(state == ValueState.INT_NEGATIVE) {
                negative = true;
            } else if(state == ValueState.ILLEGAL) {
                throw new IllegalStateException();
            }
        }
        if(state == ValueState.INT_ZERO) {
            return 0;
        } else if(state == ValueState.ILLEGAL) {
            throw new IllegalStateException();
        }
        if(negative) {
            retVal *= -1;
        }
        return retVal;
    }

    public static double parseToDouble(String stringToParse) throws IllegalStateException {
        ValueState state = ValueState.DOUBLE_INITIAL;
        double retVal = 0;
        int negativePowerCount = 0;
        boolean doCount = false;
        boolean negative = false;
        for(int i = 0; i < stringToParse.length(); ++i) {
            char c = stringToParse.charAt(i);
            state = state.nextState(c);
            if(state == ValueState.DOUBLE_PRE_DIGIT) {
                retVal *= 10;
                retVal += Character.getNumericValue(c);
            }else if(state == ValueState.DOUBLE_DOT){
                doCount = true;
            } else if(state == ValueState.DOUBLE_POST_DIGIT) {
                int digit = Character.getNumericValue(c);
                double toAdd = digit * Math.pow(10, negativePowerCount);
                retVal += toAdd;
            } else if(state == ValueState.DOUBLE_NEGATIVE) {
                negative = true;
            } else if(state == ValueState.ILLEGAL) {
                throw new IllegalStateException();
            }
            if(doCount) {
                --negativePowerCount;
            }
        }
        if(state == ValueState.INT_ZERO) {
            return 0;
        } else if(state == ValueState.ILLEGAL) {
            throw new IllegalStateException();
        }
        if(negative) {
            retVal *= -1;
        }
        return retVal;
    }
}
