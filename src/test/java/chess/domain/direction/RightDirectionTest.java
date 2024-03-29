package chess.domain.direction;

import static chess.domain.Fixtures.A3;
import static chess.domain.Fixtures.EMPTY_OBSTACLE;
import static chess.domain.Fixtures.F3;
import static chess.domain.Fixtures.G3;
import static chess.domain.Fixtures.H3;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Obstacle;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RightDirectionTest {

    @Test
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void Given_RightDirection_When_CanReachWithReachablePosition_Then_True() {
        //given
        RightDirection direction = new RightDirection(8);
        //when, then
        assertThat(direction.canReach(A3, H3, EMPTY_OBSTACLE)).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 더이상 이동이 불가능한 경우 거짓을 반환한다.")
    void Given_RightDirection_When_CanReachWithUnreachablePosition_Then_False() {
        //given
        RightDirection direction = new RightDirection(8);
        //when, then
        assertThat(direction.canReach(H3, F3, EMPTY_OBSTACLE)).isFalse();
    }

    @Test
    @DisplayName("도착위치 중간에 장애물이 있을 경우 거짓을 반환한다.")
    void Given_RightDirection_When_CanReachWithReachablePositionAndObstacle_Then_False() {
        //given
        RightDirection direction = new RightDirection(8);
        //when, then
        assertThat(direction.canReach(F3, H3, new Obstacle(List.of(G3)))).isFalse();
    }

    @Test
    @DisplayName("이동할 수 있는 방향의 개수를 모두 소진함에도 불구하고 도달하지 못할 경우 거짓을 반환한다.")
    void Given_RightDirection_When_CanReachWithUnreachableMoveCount_then_False() {
        //given
        RightDirection direction = new RightDirection(1);
        //when, then
        assertThat(direction.canReach(A3, H3, EMPTY_OBSTACLE)).isFalse();
    }
}
