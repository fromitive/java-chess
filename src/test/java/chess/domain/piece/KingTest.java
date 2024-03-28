package chess.domain.piece;

import static chess.domain.Fixtures.C3;
import static chess.domain.Fixtures.C4;
import static chess.domain.Fixtures.D3;
import static chess.domain.Fixtures.D4;
import static chess.domain.Fixtures.D5;
import static chess.domain.Fixtures.E3;
import static chess.domain.Fixtures.E4;
import static chess.domain.Fixtures.E5;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {
    private static final Position SOURCE = D4;

    static Stream<Position> possibleTarget() {
        return Stream.of(
                D5, E5, E4, E3, D3, C3, C4
        );
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("킹은 도착 위치가 비어있는 경우 이동할 수 있다.")
    void Given_King_When_CanMovePositionEmpty_Then_True(Position target) {
        //given
        Piece piece = new King(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of())).isTrue();
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("킹은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void Given_King_When_CanMovePositionEnemyPiece_Then_True(Position target) {
        //given
        Piece piece = new King(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of(target, new Queen(Color.BLACK)))).isTrue();
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("킹은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void Given_King_When_CanNotMovePositionOurTeamPiece_Then_False(Position target) {
        //given
        Piece piece = new King(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of(target, new Queen(Color.WHITE)))).isFalse();
    }
}
