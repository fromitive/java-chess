package chess.domain.movement.direction;

import chess.domain.position.Position;

public class LeftDirection extends SlidingDirection {

    public LeftDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMinimumFile()) {
            return position;
        }
        return new Position(position.file().move(-1), position.rank());
    }
}
