package chess.domain.piece;

import static chess.domain.Fixtures.A4;
import static chess.domain.Fixtures.B4;
import static chess.domain.Fixtures.C4;
import static chess.domain.Fixtures.D1;
import static chess.domain.Fixtures.D2;
import static chess.domain.Fixtures.D3;
import static chess.domain.Fixtures.D4;
import static chess.domain.Fixtures.D5;
import static chess.domain.Fixtures.D6;
import static chess.domain.Fixtures.D7;
import static chess.domain.Fixtures.D8;
import static chess.domain.Fixtures.E4;
import static chess.domain.Fixtures.F4;
import static chess.domain.Fixtures.G4;
import static chess.domain.Fixtures.H4;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    private static final Position SOURCE = D4;

    static Stream<Position> possibleTarget() {
        return Stream.of(
                A4, B4, C4, E4, F4, G4, H4, D1, D2, D3, D5, D6, D7, D8
        );
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("룩은 도착 위치가 비어있는 경우 이동할 수 있다.")
    void Given_Rook_When_CanMovePositionEmpty_Then_True(Position target) {
        //given
        Piece piece = new Rook(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of())).isTrue();
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("룩은 도착 위치에 상대편 말이 있는 경우 이동할 수 있다.")
    void Given_Rook_When_CanMovePositionEnemyPiece_Then_True(Position target) {
        //given
        Piece piece = new Rook(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of(target, new Queen(Color.BLACK)))).isTrue();
    }

    @ParameterizedTest
    @MethodSource("possibleTarget")
    @DisplayName("룩은 도착 위치에 우리편 말이 있는 경우 이동할 수 없다.")
    void Given_Rook_When_CanNotMovePositionOurTeamPiece_Then_False(Position target) {
        //given
        Piece piece = new Rook(Color.WHITE);
        //when, then
        assertThat(piece.canMove(SOURCE, target, Map.of(target, new Queen(Color.WHITE)))).isFalse();
    }

    @Test
    @DisplayName("룩은 이동 경로에 말이 있는 경우 이동할 수 없다.")
    void Given_Rook_When_CanNotMoveIfPieceIsOnDirection_Then_False() {
        //given
        Piece piece = new Rook(Color.WHITE);
        //when, then
        assertThat(piece.canMove(D4, H4, Map.of(E4, new Queen(Color.WHITE)))).isFalse();
    }
}
