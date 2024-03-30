package chess.game.status;

import chess.service.ChessService;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public interface GameStatus {
    boolean isPlayable();

    GameStatus play(final InputView inputView, final OutputView outputView, ChessService chessService);
}
