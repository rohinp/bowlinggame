package org.sthapna.bowling.game;

public interface Frame {
    int STRIKE_SCORE = 10;
    int score();
    boolean isSpare();
    boolean isStrike();
    boolean isLast();
    int _1();
    int _2();


    static Frame frame(final int _1,final int _2) {
        return new Frame() {
            @Override
            public int score() {
                return _1 + _2;
            }

            @Override
            public boolean isSpare() {
                return score() == STRIKE_SCORE;
            }

            @Override
            public boolean isStrike() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public int _1() {
                return _1;
            }

            @Override
            public int _2() {
                return _2;
            }
        };
    }

    static Frame onePin(final int _1) {
        return new Frame() {
            @Override
            public int score() {
                return _1;
            }

            @Override
            public boolean isSpare() {
                return false;
            }

            @Override
            public boolean isStrike() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public int _1() {
                return _1;
            }

            @Override
            public int _2() {
                return 0;
            }
        };
    }

    static Frame strike() {
        return new Frame() {

            @Override
            public int score() {
                return STRIKE_SCORE;
            }

            @Override
            public boolean isSpare() {
                return false;
            }

            @Override
            public boolean isStrike() {
                return true;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public int _1() {
                return STRIKE_SCORE;
            }

            @Override
            public int _2() {
                throw new OnlySingleBowlForStrike();
            }
        };
    }

    static Frame last(int _1, int _2, int _3) {
        return new Frame() {
            @Override
            public int score() {
                return _1 + _2 + _3;
            }

            @Override
            public boolean isSpare() {
                return _1 + _2 == STRIKE_SCORE;
            }

            @Override
            public boolean isStrike() {
                return _1 == STRIKE_SCORE;
            }

            @Override
            public boolean isLast() {
                return true;
            }

            @Override
            public int _1() {
                return _1;
            }

            @Override
            public int _2() {
                return _2;
            }
        };
    }

    static Frame spare(int _1) {
        return frame(_1, STRIKE_SCORE - _1);
    }


    class OnlySingleBowlForStrike extends RuntimeException {}
}
