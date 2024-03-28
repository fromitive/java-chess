package chess.domain.movement.direction;

import chess.domain.position.Position;

public class DownDirection extends SlidingDirection {

    public DownDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMinimumRank()) {
            return position;
        }
        return new Position(position.file(), position.rank().move(-1));
    }
}
