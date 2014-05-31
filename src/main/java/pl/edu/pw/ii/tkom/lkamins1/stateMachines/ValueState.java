package pl.edu.pw.ii.tkom.lkamins1.stateMachines;

/**
 * Created by lucas on 16.05.14.
 */
public enum ValueState {
    INT_INITIAL {
        @Override
        public ValueState nextState(char c) {
            if(c == '-') {
                return INT_NEGATIVE;
            } else if(c == '0') {
                return INT_ZERO;
            } else if(Character.isDigit(c)) {
                return INT_DIGIT;
            } else {
                return ILLEGAL;
            }
        }
    },
    INT_NEGATIVE {
        @Override
        public ValueState nextState(char c) {
            if(c == '0' || !Character.isDigit(c)) {
                return ILLEGAL;
            } else {
                return INT_DIGIT;
            }
        }
    },
    INT_DIGIT {
        @Override
        public ValueState nextState(char c) {
            if(Character.isDigit(c)) {
                return INT_DIGIT;
            } else {
                return ILLEGAL;
            }
        }
    },
    INT_ZERO {
        @Override
        public ValueState nextState(char c) {
            return ILLEGAL;
        }
    },
    DOUBLE_INITIAL {
        @Override
        public ValueState nextState(char c) {
            if(c == '-') {
                return DOUBLE_NEGATIVE;
            } else if(c == '0') {
                return DOUBLE_PRE_ZERO;
            } else if(Character.isDigit(c)) {
                return DOUBLE_PRE_DIGIT;
            } else {
                return ILLEGAL;
            }
        }
    },
    DOUBLE_NEGATIVE {
        @Override
        public ValueState nextState(char c) {
            if(c == '0') {
                return DOUBLE_PRE_ZERO;
            } else if(Character.isDigit(c)) {
                return DOUBLE_PRE_DIGIT;
            } else {
                return ILLEGAL;
            }
        }
    },
    DOUBLE_PRE_ZERO {
        @Override
        public ValueState nextState(char c) {
            if(c == '.') {
                return DOUBLE_DOT;
            } else {
                return ILLEGAL;
            }
        }
    },
    DOUBLE_PRE_DIGIT {
        @Override
        public ValueState nextState(char c) {
            if(c == '.') {
                return DOUBLE_DOT;
            } else if(Character.isDigit(c)){
                return DOUBLE_PRE_DIGIT;
            } else {
                return ILLEGAL;
            }
        }
    },
    DOUBLE_DOT {
        @Override
        public ValueState nextState(char c) {
            if(Character.isDigit(c)) {
                return DOUBLE_POST_DIGIT;
            } else {
                return ILLEGAL;
            }
        }
    },
    DOUBLE_POST_DIGIT {
        @Override
        public ValueState nextState(char c) {
            if(Character.isDigit(c)) {
                return DOUBLE_POST_DIGIT;
            } else {
                return ILLEGAL;
            }
        }
    },
    ILLEGAL {
        @Override
        public ValueState nextState(char c) {
            return ILLEGAL;
        }
    };

    public abstract ValueState nextState(char c);
}
