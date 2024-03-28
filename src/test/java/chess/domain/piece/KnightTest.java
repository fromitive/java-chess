package chess.domain.piece;

import static chess.domain.Fixtures.B3;
import static chess.domain.Fixtures.B5;
import static chess.domain.Fixtures.C2;
import static chess.domain.Fixtures.C6;
import static chess.domain.Fixtures.D4;
import static chess.domain.Fixtures.E2;
import static chess.domain.Fixtures.E6;
import static chess.domain.Fixtures.F3;
import static chess.domain.Fixtures.F5;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {
    private static final Position SOURCE = D4;

    static Stream<Position> possibleTarget() {
        return Stream.of(
                E6, E2, C6, C2, F5, F3, B5, B3
        );
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("나이트는 도착 위치가 비어있는 경우 이동할 수 있다.")
    void Given_Knight_When_CanMovePositionEmpty_Then_True(Position target) {
        //given
        Piece piece = new Knight(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of())).isTrue();
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("나이트는 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void Given_Knight_When_CanMovePositionEnemyPiece_Then_True(Position target) {
        //given
        Piece piece = new Knight(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of(target, new Queen(Color.BLACK)))).isTrue();
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("나이트는 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void Given_Knight_When_CanNotMovePositionOurTeamPiece_Then_False(Position target) {
        //given
        Piece piece = new Knight(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of(target, new Queen(Color.WHITE)))).isFalse();
    }
}
