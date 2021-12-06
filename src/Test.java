import core.Game;
import players.EXP3.exp3Player;
import players.KeyController;
import players.OSLAPlayer;
import players.Player;
import players.RAVE.MCRavePlayer;
import players.SimplePlayer;
import players.mcts.MCTSParams;
import players.mcts.MCTSPlayer;
import players.mctsPessimistic.MCTSPessimisticPlayer;
import players.mctsPessimistic.pessimisticParams;
import players.rhea.RHEAPlayer;
import players.rhea.utils.Constants;
import players.rhea.utils.RHEAParams;
import players.smoothUCT.smoothUCTPlayer;
import utils.Types;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        // Game parameters
        long seed = System.currentTimeMillis();
        int boardSize = Types.BOARD_SIZE;
        Types.GAME_MODE gameMode = Types.GAME_MODE.FFA;
        boolean useSeparateThreads = false;

        Game game = new Game(seed, boardSize, Types.GAME_MODE.FFA, "");

        // Key controllers for human player s (up to 2 so far).
        KeyController ki1 = new KeyController(true);
        KeyController ki2 = new KeyController(false);

        // Create players
        ArrayList<Player> players = new ArrayList<>();
        int playerID = Types.TILETYPE.AGENT0.getKey();

        MCTSParams mctsParams = new MCTSParams();
        mctsParams.stop_type = mctsParams.STOP_ITERATIONS;
        mctsParams.heuristic_method = mctsParams.CUSTOM_HEURISTIC;

        RHEAParams rheaParams = new RHEAParams();
        rheaParams.heurisic_type = Constants.CUSTOM_HEURISTIC;

        pessimisticParams pessparams = new pessimisticParams();
        pessparams.heuristic_method = pessparams.PESSIMISTIC_HEURISTIC;

       // players.add(new MCTSPlayer(seed, playerID++, mctsParams));
        //players.add(new MCTSPlayer(seed, playerID++, mctsParams));

//        players.add(new SimplePlayer(seed, playerID++));
        //players.add(new RHEAPlayer(seed, playerID++, rheaParams));
//        players.add(new SimplePlayer(seed, playerID++));
//        players.add(new MCTSPlayer(seed, playerID++, new MCTSParams()));
//        players.add(new RHEAPlayer(seed, playerID++, rheaParams));



//        players.add(new exp3Player(seed, playerID++, mctsParams));
        players.add(new MCTSPessimisticPlayer(seed, playerID++, pessparams));
//        players.add(new MCTSPessimisticPlayer(seed, playerID++, pessparams));
        players.add(new smoothUCTPlayer(seed, playerID++, mctsParams));

        players.add(new exp3Player(seed, playerID++, mctsParams));
//        players.add(new SimplePlayer(seed, playerID++));
//        players.add(new SimplePlayer(seed, playerID++));
        players.add(new MCRavePlayer(seed, playerID++, mctsParams));
        // Make sure we have exactly NUM_PLAYERS players
        assert players.size() == Types.NUM_PLAYERS : "There should be " + Types.NUM_PLAYERS +
                " added to the game, but there are " + players.size();


        //Assign players and run the game.
        game.setPlayers(players);

        Types.DEFAULT_VISION_RANGE = 4;
        //Run a single game with the players
        Run.runGame(game, ki1, ki2, useSeparateThreads);

        /* Uncomment to run the replay of the previous game: */
//        if (game.isLogged()){
//            Game replay = Game.getLastReplayGame();
//            Run.runGame(replay, ki1, ki2, useSeparateThreads);
//            assert(replay.getGameState().equals(game.getGameState()));
//        }



        /* Run with no visuals, N Times: */
//        int N = 20;
//        Run.runGames(game, new long[]{seed}, N, useSeparateThreads);

    }

}
