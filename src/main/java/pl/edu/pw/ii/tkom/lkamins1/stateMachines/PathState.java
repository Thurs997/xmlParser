package pl.edu.pw.ii.tkom.lkamins1.stateMachines;

/**
 * Created by lucas on 01.06.14.
 */
public enum PathState {
    INTIAL {
        private boolean terminal = false;
        @Override
        public boolean isTerminal() {return terminal;}
        @Override
        public PathState nextState(char ch) {
            switch(ch) {
                case '/': return PATH_INIT;
                default : return ILLEGAL;
            }
        }
    },
    PATH_INIT {
        private boolean terminal = false;
        @Override
        public boolean isTerminal() {return terminal;}
        @Override
        public PathState nextState(char ch) {
            switch(ch) {
                case ' ':
                case '\t':
                case '\n':
                case '/':
                case '=': return ILLEGAL;
                default: return PATH;
            }
        }
    },
    PATH {
        private boolean terminal = false;
        @Override
        public boolean isTerminal() {return terminal;}
        @Override
        public PathState nextState(char ch) {
            switch (ch) {
                case ' ':
                case '\t':
                case '\n': return ILLEGAL;
                case '=': return CONSTRAINT_INIT;
                case '/': return PATH_END;
                default: return PATH;
            }
        }
    },
    PATH_END {
        private boolean terminal = true;
        @Override
        public boolean isTerminal() {return terminal;}
        @Override
        public PathState nextState(char ch) {
            switch (ch) {
                default: return ILLEGAL;
            }
        }
    },
    CONSTRAINT_INIT {
        private boolean terminal = false;
        @Override
        public boolean isTerminal() {return terminal;}
        @Override
        public PathState nextState(char ch) {
            switch (ch) {
                case '\'': return CONSTRAINT_OPENQUOTE;
                default: return ILLEGAL;
            }
        }
    },
    CONSTRAINT_OPENQUOTE {
        private boolean terminal = false;
        @Override
        public boolean isTerminal() {return terminal;}
        @Override
        public PathState nextState(char ch) {
            switch (ch) {
                case ' ':
                case '\t':
                case '\n':
                case '"': return ILLEGAL;
                default: return CONSTRAINT;
            }
        }
    },
    CONSTRAINT {
        private boolean terminal = false;
        @Override
        public boolean isTerminal() {return terminal;}
        @Override
        public PathState nextState(char ch) {
            switch (ch) {
                case ' ':
                case '\t':
                case '\n': return ILLEGAL;
                case '\'': return CONSTRAINT_CLOSEQUOTE;
                default: return CONSTRAINT;
            }
        }
    },
    CONSTRAINT_CLOSEQUOTE {
        private boolean terminal = false;
        @Override
        public boolean isTerminal() {return terminal;}
        @Override
        public PathState nextState(char ch) {
            switch (ch) {
                case '/': return CONSTRAINT_END;
                default: return ILLEGAL;
            }
        }
    },
    CONSTRAINT_END {
        private boolean terminal = true;
        @Override
        public boolean isTerminal() {return terminal;}
        @Override
        public PathState nextState(char ch) {
            switch (ch) {
                default: return ILLEGAL;
            }
        }
    },

    ILLEGAL {
        private boolean terminal = true;
        @Override
        public boolean isTerminal() {return terminal;}
        @Override
        public PathState nextState(char ch) {
            switch (ch) {
                default: return ILLEGAL;
            }
        }
    };
    public abstract PathState nextState(char ch);
    public abstract boolean isTerminal();
}
