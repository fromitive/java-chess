package chess.domain.movement.direction;

import chess.domain.position.Position;
import java.util.List;

public class CombinationDirection implements Direction {
    private final List<Direction> directions;

    public CombinationDirection(final Direction... directions) {
        this.directions = List.of(directions);
    }

    @Override
    public boolean canReach(final Position source, final Position target, final List<Position> obstacle) {
        return directions.stream().anyMatch(direction -> direction.canReach(source, target, obstacle));
    }
}
