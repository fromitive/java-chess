package chess.domain.movement.direction;

import chess.domain.position.Position;
import java.util.List;

public interface Direction {

    boolean canReach(final Position source, final Position target, final List<Position> obstacle);
}
