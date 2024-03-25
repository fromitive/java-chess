package chess.domain.movement.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UpDirectionTest {

    @Test
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void Given_UpDirection_When_CanReachWithReachablePosition_then_True() {
        //given
        UpDirection direction = new UpDirection(8);
        Position source = new Position(8, 1);
        Position target = new Position(8, 3);
        //when, then
        assertThat(direction.canReach(source, target, List.of())).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 더이상 이동이 불가능한 경우 거짓을 반환한다.")
    void Given_UpDirection_When_CanReachWithUnreachablePosition_Then_False() {
        //given
        UpDirection direction = new UpDirection(8);
        Position source = new Position(1, 8);
        Position target = new Position(1, 7);
        //when, then
        assertThat(direction.canReach(source, target, List.of())).isFalse();
    }

    @Test
    @DisplayName("도착위치 중간에 장애물이 있을 경우 거짓을 반환한다.")
    void Given_UpDirection_When_CanReachWithReachablePositionAndObstacle_Then_False() {
        //given
        UpDirection direction = new UpDirection(8);
        Position source = new Position(3, 1);
        Position target = new Position(3, 8);
        //when, then
        assertThat(direction.canReach(source, target, List.of(new Position(3, 4)))).isFalse();
    }

    @Test
    @DisplayName("이동할 수 있는 방향의 개수를 모두 소진함에도 불구하고 도달하지 못할 경우 거짓을 반환한다.")
    void Given_UpDirection_When_CanReachWithUnreachableMoveCount_then_False() {
        //given
        UpDirection direction = new UpDirection(1);
        Position source = new Position(8, 1);
        Position target = new Position(6, 1);
        //when, then
        assertThat(direction.canReach(source, target, List.of())).isFalse();
    }
}

