package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {

    private static final List<Position> POSITION_LIST;
    static {
        POSITION_LIST = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> new Position(file, rank))).toList();
    }
    File file;
    Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return POSITION_LIST.stream().filter(position -> position.file == file && position.rank == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 파일과 랭크로 위치를 생성할 수 없습니다."));
    }

    public boolean isMinimumFile() {
        return file.isMinimum();
    }

    public boolean isMaximumFile() {
        return file.isMaximum();
    }

    public boolean isMinimumRank() {
        return rank.isMinimum();
    }

    public boolean isMaximumRank() {
        return rank.isMaximum();
    }

    public boolean isNextPositionInRange(final Vector vector) {
        return file.canMoveNext(vector.getFileVector()) && rank.canMoveNext(vector.getRankVector());
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
