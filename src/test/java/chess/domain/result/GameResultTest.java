package chess.domain.result;

import static chess.domain.Fixtures.A7;
import static chess.domain.Fixtures.B8;
import static chess.domain.Fixtures.C7;
import static chess.domain.Fixtures.C8;
import static chess.domain.Fixtures.D7;
import static chess.domain.Fixtures.F2;
import static chess.domain.Fixtures.F3;
import static chess.domain.Fixtures.F4;
import static chess.domain.Fixtures.G4;
import static chess.domain.Fixtures.H3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    //abcdefgh
    //.KR..... - 8 (5, 0)
    //P.PB.... - 7 (4, 0)
    //........ - 6 (0, 0)
    //........ - 5 (0, 0)
    //.....nq. - 4 (0, 11.5)
    //.....p.p - 3 (0, 1)
    //.....k.. - 2 (0, 0)
    //........ - 1
    //              점수
    //              b  w
    //              9  12.5

    private static final Board GIVEN_BOARD = new Board(
            Map.of(
                    B8, Piece.of(PieceType.KING, Color.BLACK),
                    C8, Piece.of(PieceType.ROOK, Color.BLACK),
                    A7, Pawn.of(Color.BLACK),
                    C7, Pawn.of(Color.BLACK),
                    D7, Piece.of(PieceType.BISHOP, Color.BLACK),
                    F4, Piece.of(PieceType.KNIGHT, Color.WHITE),
                    G4, Piece.of(PieceType.QUEEN, Color.WHITE),
                    F3, Pawn.of(Color.WHITE),
                    H3, Pawn.of(Color.WHITE),
                    F2, Piece.of(PieceType.KING, Color.BLACK)
            ));

    @Test
    @DisplayName("각 체스 색상의 점수를 계산한다.")
    void Given_GameResultWithWhiteKingBoard_When_calcuateScore_Then_ReturnWhiteColor() {
        //given
        GameResult gameResult = new GameResult();

        //when, then
        assertAll(
                () -> assertThat(gameResult.calcuateScore(GIVEN_BOARD, Color.WHITE)).isEqualTo(new Score(12.5)),
                () -> assertThat(gameResult.calcuateScore(GIVEN_BOARD, Color.BLACK)).isEqualTo(new Score(9))
        );
    }
}
