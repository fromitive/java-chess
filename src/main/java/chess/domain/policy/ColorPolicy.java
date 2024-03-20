package chess.domain.policy;

import chess.domain.Color;

public class ColorPolicy implements Policy {

    private final Color color;

    public ColorPolicy(final Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(final Color color, final boolean firstMove, final boolean existEnemy) {
        return this.color == color;
    }
}
