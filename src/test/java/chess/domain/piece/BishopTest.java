package chess.domain.piece;

import static chess.domain.Fixtures.A1;
import static chess.domain.Fixtures.A7;
import static chess.domain.Fixtures.B2;
import static chess.domain.Fixtures.B6;
import static chess.domain.Fixtures.C3;
import static chess.domain.Fixtures.D4;
import static chess.domain.Fixtures.E5;
import static chess.domain.Fixtures.F2;
import static chess.domain.Fixtures.F6;
import static chess.domain.Fixtures.G1;
import static chess.domain.Fixtures.G7;
import static chess.domain.Fixtures.H8;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    private static final Position SOURCE = D4;

    static Stream<Position> possibleTarget() {
        return Stream.of(
                A1, B2, C3, E5, F6, G7, H8, G1, F2, E5, B6, A7
        );
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("비숍은 도착 위치가 비어있는 경우 이동할 수 있다.")
    void Given_Bishop_When_CanMovePositionEmpty_Then_True(Position target) {
        //given
        Piece piece = new Bishop(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of())).isTrue();
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("비숍은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void Given_Bishop_When_CanMovePositionEnemyPiece_Then_True(Position target) {
        //given
        Piece piece = new Bishop(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of(target, new Bishop(Color.BLACK)))).isTrue();
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("비숍은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void Given_Bishop_When_CanNotMovePositionOurTeamPiece_Then_False(Position target) {
        //given
        Piece piece = new Bishop(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, SOURCE, Map.of(target, new Bishop(Color.WHITE)))).isFalse();
    }

    @Test
    @DisplayName("비숍은 이동 경로에 말이 있는 경우 이동할 수 없다.")
    void Given_Bishop_When_CanNotMoveIfPieceIsOnDirection_Then_False() {
        //given
        Piece piece = new Bishop(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, H8, Map.of(F6, new Bishop(Color.WHITE)))).isFalse();
    }
}
