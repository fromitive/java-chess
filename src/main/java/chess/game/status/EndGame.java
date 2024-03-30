package chess.game.status;

import chess.domain.board.Board;
import chess.service.ChessService;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class EndGame implements GameStatus {

    private final Board board;

    public EndGame(Board board) {
        this.board = board;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play(InputView inputView, OutputView outputView, ChessService chessService) {
        outputView.printWinnerColor(board.getAloneKingColor());
        initializeBoard(chessService);
        return new TerminateGame();
    }

    private void initializeBoard(ChessService chessService) {
        chessService.initializeChess();
    }
}
