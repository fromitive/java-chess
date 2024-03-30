package chess.game.status;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.service.ChessService;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class SaveGame implements GameStatus {

    private final Board board;
    private final Color color;

    public SaveGame(Board board, Color color) {
        this.board = board;
        this.color = color;
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public GameStatus play(InputView inputView, OutputView outputView, ChessService chessService) {
        chessService.saveChess(board, color);
        outputView.printSave();
        return new TerminateGame();
    }
}
