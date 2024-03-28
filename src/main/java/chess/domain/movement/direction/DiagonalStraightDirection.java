package chess.domain.movement.direction;

import chess.domain.position.Position;
import java.util.List;

public class DiagonalStraightDirection implements Direction {
    private final CombinationDirection combinationDirection;

    public DiagonalStraightDirection(int moveCount) {
        this.combinationDirection = new CombinationDirection(
                new DiagonalDirection(moveCount), new StrightDirection(moveCount));
    }

    @Override
    public boolean canReach(Position source, Position target, List<Position> obstacle) {
        return combinationDirection.canReach(source, target, obstacle);
    }
}
