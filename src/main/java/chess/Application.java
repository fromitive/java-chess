package chess;

import chess.game.ChessGame;
import chess.service.ChessService;
import chess.service.dao.db.DatabaseChessDAO;
import chess.service.dao.db.MySQLConfiguration;
import chess.view.input.InputView;
import chess.view.output.OutputView;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in);
             var connection = MySQLConfiguration.getConnection()) {
            ChessGame chessGame = new ChessGame(new InputView(scanner), new OutputView());
            chessGame.start(new ChessService(new DatabaseChessDAO(connection)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
