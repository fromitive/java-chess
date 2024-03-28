package chess.domain.movement.direction;

import static chess.domain.Fixtures.A1;
import static chess.domain.Fixtures.F1;
import static chess.domain.Fixtures.G1;
import static chess.domain.Fixtures.H1;
import static chess.domain.Fixtures.H8;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LeftDirectionTest {

    @Test
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void Given_LeftDirection_When_CanReachWithReachablePosition_Then_True() {
        //given
        LeftDirection direction = new LeftDirection(8);
        //when, then
        assertThat(direction.canReach(H1, A1, List.of())).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 더이상 이동이 불가능한 경우 거짓을 반환한다.")
    void Given_LeftDirection_When_CanReachWithUnreachablePosition_Then_False() {
        //given
        LeftDirection direction = new LeftDirection(8);
        //when, then
        assertThat(direction.canReach(H1, H8, List.of())).isFalse();
    }

    @Test
    @DisplayName("도착위치 중간에 장애물이 있을 경우 거짓을 반환한다.")
    void Given_LeftDirection_When_CanReachWithReachablePositionAndObstacle_Then_False() {
        //given
        LeftDirection direction = new LeftDirection(8);
        //when, then
        assertThat(direction.canReach(H1, F1, List.of(G1))).isFalse();
    }

    @Test
    @DisplayName("이동할 수 있는 방향의 개수를 모두 소진함에도 불구하고 도달하지 못할 경우 거짓을 반환한다.")
    void Given_LeftDirection_When_CanReachWithUnreachableMoveCount_Then_False() {
        //given
        LeftDirection direction = new LeftDirection(1);
        //when, then
        assertThat(direction.canReach(H1, F1, List.of())).isFalse();
    }
}
