package chess.domain.direction;

import chess.domain.Obstacle;
import chess.domain.position.Position;
import java.util.stream.Stream;

abstract class SlidingDirection implements Direction {

    private final int moveCount;

    protected SlidingDirection(final int moveCount) {
        this.moveCount = moveCount;
    }

    public boolean canReach(final Position source, final Position target, final Obstacle obstacle) {
        return Stream.iterate(next(source), now -> !obstacle.isBlocked(now, target), this::next)
                .limit(moveCount)
                .anyMatch(position -> position.equals(target));
    }

    abstract Position next(Position position);
}
