package chess.domain.movement.direction;

import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;

public class FirstRankMoveDirection implements Direction {

    private final Direction direction;
    private final Rank startRank;

    public FirstRankMoveDirection(Direction direction, Rank startRank) {
        this.direction = direction;
        this.startRank = startRank;
    }

    @Override
    public boolean canReach(Position source, Position target, List<Position> obstacle) {
        if (source.rank().equals(startRank)) {
            return direction.canReach(source, target, obstacle);
        }
        return false;
    }
}
