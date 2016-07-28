package org.sthapna.bowling.game.linestrategies;

import org.sthapna.bowling.game.Frame;
import java.util.List;
import static org.sthapna.bowling.game.Frame.STRIKE_SCORE;
import static org.sthapna.bowling.util.ListUtil.head;
import static org.sthapna.bowling.util.ListUtil.next;
import static org.sthapna.bowling.util.ListUtil.nextToNext;

enum Operations {

    ON_LAST() {
        @Override
        int calculate(List<Frame> line) {
            Frame c = head(line);
            if(c.isStrike()) return c.score() - c._1()  + STRIKE_SCORE;
            if(c.isSpare()) return  c.score() - c._1() - c._2() + STRIKE_SCORE;
            return c.score();
        }
    },

    ON_STRIKE() {
        @Override
        int calculate(List<Frame> line) {
            if(next(line).isLast()) return next(line)._1() + next(line)._2() + STRIKE_SCORE;
            else if(next(line).isStrike()) return 2 * STRIKE_SCORE + nextToNext(line)._1();
            else return STRIKE_SCORE + next(line).score();
        }
    },

    ON_SPARE() {
        @Override
        int calculate(List<Frame> line) {
            return next(line)._1() + STRIKE_SCORE;
        }
    },

    DEFAULT() {
        @Override
        int calculate(List<Frame> line) {
            return head(line).score();
        }
    };

    static int apply(final List<Frame> line) {
        if(head(line).isLast()) return Operations.ON_LAST.calculate(line);
        if(head(line).isStrike())return Operations.ON_STRIKE.calculate(line);
        if(head(line).isSpare()) return Operations.ON_SPARE.calculate(line);
        return Operations.DEFAULT.calculate(line);
    }

    abstract int calculate(final List<Frame> line);
}
