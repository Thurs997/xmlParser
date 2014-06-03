package pl.edu.pw.ii.tkom.lkamins1.stateMachines;

/**
 * Created by lucas on 12.05.14.
 */
public enum TokenState {
    INITIAL {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '<': return TAG_INIT;
                case ' ':
                case '\n':
                case '\t': return INITIAL;
                default : return VALUE;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_INIT {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '!': return TAG_COMMENT_OPEN_EXCLAMATION;
                case '/': return TAG_CLOSE_INIT;
                case '>': return ILLEGAL;
                default : return TAG_OPEN_INIT;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_INIT {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case ' ': return TAG_OPEN_WHITESPACE;
                case '>': return ILLEGAL;
                default : return TAG_OPEN_TAG;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TAG {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case ' ': return TAG_OPEN_WHITESPACE;
                case '>': return ILLEGAL;
                default : return TAG_OPEN_TAG;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_WHITESPACE {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 't': return TAG_OPEN_TYPE_T;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_T {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'y': return TAG_OPEN_TYPE_Y;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_Y {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'p': return TAG_OPEN_TYPE_P;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_P {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'e': return TAG_OPEN_TYPE_E;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_E {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '=': return TAG_OPEN_TYPE_EQ;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_EQ {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '"': return TAG_OPEN_TYPE_OPENQUOTE;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_OPENQUOTE {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'i': return TAG_OPEN_TYPE_INT_I;
                case 'd': return TAG_OPEN_TYPE_DOUBLE_D;
                case 's': return TAG_OPEN_TYPE_UNKNOWN_S;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_INT_I {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'n': return TAG_OPEN_TYPE_INT_N;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_INT_N {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 't': return TAG_OPEN_TYPE_INT_T;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_INT_T {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '"': return TAG_OPEN_TYPE_CLOSEQUOTE;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_DOUBLE_D {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'o': return TAG_OPEN_TYPE_DOUBLE_O;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_DOUBLE_O {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'u': return TAG_OPEN_TYPE_DOUBLE_U;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_DOUBLE_U {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'b': return TAG_OPEN_TYPE_DOUBLE_B;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_DOUBLE_B {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'l': return TAG_OPEN_TYPE_DOUBLE_L;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_DOUBLE_L {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'e': return TAG_OPEN_TYPE_DOUBLE_E;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_DOUBLE_E {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '"': return TAG_OPEN_TYPE_CLOSEQUOTE;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_UNKNOWN_S {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 't': return TAG_OPEN_TYPE_STRING_T;
                case 'e': return TAG_OPEN_TYPE_SECTION_E;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_STRING_T {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'r': return TAG_OPEN_TYPE_STRING_R;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_STRING_R {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'i': return TAG_OPEN_TYPE_STRING_I;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_STRING_I {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'n': return TAG_OPEN_TYPE_STRING_N;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_STRING_N {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'g': return TAG_OPEN_TYPE_STRING_G;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_STRING_G {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '"': return TAG_OPEN_TYPE_CLOSEQUOTE;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_SECTION_E {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'c': return TAG_OPEN_TYPE_SECTION_C;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_SECTION_C {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 't': return TAG_OPEN_TYPE_SECTION_T;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_SECTION_T {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'i': return TAG_OPEN_TYPE_SECTION_I;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_SECTION_I {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'o': return TAG_OPEN_TYPE_SECTION_O;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_SECTION_O {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case 'n': return TAG_OPEN_TYPE_SECTION_N;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_SECTION_N {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '"': return TAG_OPEN_TYPE_CLOSEQUOTE;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_TYPE_CLOSEQUOTE {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case ' ': return TAG_OPEN_TYPE_CLOSEQUOTE;
                case '>': return TAG_OPEN_END;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_OPEN_END {
        boolean terminal = true;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_CLOSE_INIT {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '>': return ILLEGAL;
                default : return TAG_CLOSE_TAG;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_CLOSE_TAG {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '>': return TAG_CLOSE_END;
                default : return TAG_CLOSE_TAG;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_CLOSE_END {
        boolean terminal = true;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_COMMENT_OPEN_EXCLAMATION {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '-': return TAG_COMMENT_OPEN_HYPHEN1;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_COMMENT_OPEN_HYPHEN1 {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '-': return TAG_COMMENT_OPEN_HYPHEN2;
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_COMMENT_OPEN_HYPHEN2 {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '-': return TAG_COMMENT_CLOSE_HYPHEN1;
                default : return TAG_COMMENT_TEXT;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_COMMENT_TEXT {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '-': return TAG_COMMENT_CLOSE_HYPHEN1;
                default : return TAG_COMMENT_TEXT;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_COMMENT_CLOSE_HYPHEN1 {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '-': return TAG_COMMENT_CLOSE_HYPHEN2;
                default : return TAG_COMMENT_TEXT;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_COMMENT_CLOSE_HYPHEN2 {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '>': return TAG_COMMENT_END;
                default : return TAG_COMMENT_TEXT;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    TAG_COMMENT_END {
        boolean terminal = true;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    VALUE {
        boolean terminal = false;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                case '<': return VALUE_END;
                default : return VALUE;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    VALUE_END {
        boolean terminal = true;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    },
    ILLEGAL {
        boolean terminal = true;
        @Override
        public TokenState nextState(char ch) {
            switch (ch) {
                default : return ILLEGAL;
            }
        }
        @Override
        public boolean isTerminal() {
            return terminal;
        }
    };



    public abstract TokenState nextState(char ch);
    public abstract boolean isTerminal();

}
