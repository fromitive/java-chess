package chess.domain.movement;

import static chess.domain.Fixtures.A1;
import static chess.domain.Fixtures.B2;
import static chess.domain.Fixtures.C2;
import static chess.domain.Fixtures.C4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ObstaclesTest {
    @Test
    @DisplayName("이동 경로의 장애물을 생성할 때, 출발지와 도착지는 장애물에 포함되지 않는다.")
    void Given_ObstaclesWithSourceTargetPosition_When_IsBlockedSourceAndTarget_Then_False() {
        //given, when, then
        assertAll(
                () -> assertThat(new Obstacles(A1, B2, List.of(A1, B2, C2, C4)).isBlocked(A1)).isFalse(),
                () -> assertThat(new Obstacles(A1, B2, List.of(A1, B2, C2, C4)).isBlocked(B2)).isFalse()
        );
    }
}
