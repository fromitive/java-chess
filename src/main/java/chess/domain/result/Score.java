package chess.domain.result;

import java.util.Objects;

public class Score {

    public static final Score ZERO = new Score(0.0);

    private final double value;

    public Score(double value) {
        validatePositive(value);
        this.value = value;
    }

    private void validatePositive(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("음수로 점수를 초기화 할 수 없습니다.");
        }
    }

    public Score add(Score score) {
        return new Score(value + score.value);
    }

    public Score subtract(Score score) {
        return new Score(value - score.value);
    }

    public Score multiply(double ratio) {
        return new Score(value * ratio);
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return Double.compare(value, score.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

