package chess.domain.position;

import java.util.List;

public class Obstacle {
    private final List<Position> obstacles;

    public Obstacle(List<Position> obstacles) {
        this.obstacles = obstacles;
    }

    public boolean isBlocked(Position position, Position target) {
        return obstacles.contains(position) && !position.equals(target);
    }
}
