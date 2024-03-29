package chess.domain.movementpolicy;

import chess.domain.direction.Direction;
import chess.domain.position.Obstacle;
import chess.domain.position.Position;

public class SameMovementPolicy implements MovementPolicy {

    private final Direction direction;

    public SameMovementPolicy(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean canAttack(Position source, Position target, Obstacle obstacle) {
        return direction.canReach(source, target, obstacle);
    }

    @Override
    public boolean canMove(Position source, Position target, Obstacle obstacle) {
        return direction.canReach(source, target, obstacle);
    }
}
