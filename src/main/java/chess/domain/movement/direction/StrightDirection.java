package chess.domain.movement.direction;

import chess.domain.position.Position;
import java.util.List;

public class StrightDirection implements Direction {
    private final CombinationDirection combinationDirection;

    public StrightDirection(int moveCount) {
        this.combinationDirection = new CombinationDirection(
                new UpDirection(moveCount), new DownDirection(moveCount),
                new LeftDirection(moveCount), new RightDirection(moveCount));
    }

    @Override
    public boolean canReach(final Position source, final Position target, final List<Position> obstacle) {
        return combinationDirection.canReach(source, target, obstacle);
    }
}
