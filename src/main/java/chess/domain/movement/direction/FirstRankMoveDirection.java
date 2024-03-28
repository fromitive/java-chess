package chess.domain.movement.direction;

import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;

public class FirstRankMoveDirection implements Direction {

    private final Direction direction;
    private final Rank startRank;

    public FirstRankMoveDirection(final Direction direction, final Rank startRank) {
        this.direction = direction;
        this.startRank = startRank;
    }

    @Override
    public boolean canReach(final Position source, final Position target, final List<Position> obstacle) {
        if (source.rank().equals(startRank)) {
            return direction.canReach(source, target, obstacle);
        }
        return false;
    }
}
