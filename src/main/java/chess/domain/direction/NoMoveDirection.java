package chess.domain.direction;

import chess.domain.position.Obstacle;
import chess.domain.position.Position;

public class NoMoveDirection implements Direction {

    @Override
    public boolean canReach(Position source, Position target, Obstacle obstacle) {
        return false;
    }
}
