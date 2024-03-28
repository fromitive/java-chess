package chess.domain.movement.direction;

import chess.domain.position.Position;

public class DownLeftDirection extends SlidingDirection {

    public DownLeftDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMinimumRank() || position.isMinimumFile()) {
            return position;
        }
        return new Position(position.file().move(-1), position.rank().move(-1));
    }
}
