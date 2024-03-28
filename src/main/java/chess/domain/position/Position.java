package chess.domain.position;

public record Position(File file, Rank rank) {
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
        return file.canMoveNext(vector.getFileVector()) && file.canMoveNext(vector.getRankVector());
    }
}
