package chess.domain.movement.direction;

import chess.domain.position.Position;

public class UpLeftDirection extends SlidingDirection {

    public UpLeftDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMaximumRank() || position.isMinimumFile()) {
            return position;
        }
        return new Position(position.file().move(-1), position.rank().move(1));
    }
}
