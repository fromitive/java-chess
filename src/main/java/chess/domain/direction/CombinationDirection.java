package chess.domain.direction;

import chess.domain.Obstacle;
import chess.domain.position.Position;
import java.util.List;

public class CombinationDirection implements Direction {
    private final List<Direction> directions;

    public CombinationDirection(final Direction... directions) {
        this.directions = List.of(directions);
    }

    @Override
    public boolean canReach(final Position source, final Position target, final Obstacle obstacle) {
        return directions.stream().anyMatch(direction -> direction.canReach(source, target, obstacle));
    }
}
