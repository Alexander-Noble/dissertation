package players.mctsPessimistic;

import core.GameState;
import players.Player;
import players.mcts.MCTSParams;
import players.optimisers.ParameterizedPlayer;
import utils.ElapsedCpuTimer;
import utils.Types;

import java.util.ArrayList;
import java.util.Random;

public class MCTSPessmisticPlayer extends ParameterizedPlayer {

    public MCTSParams params;

    private Random randSeed;

    public  Types.ACTIONS[] actions;

    public MCTSPessmisticPlayer(long seed, int id) { this(seed, id, new MCTSParams())}

    @Override
    public Types.ACTIONS act(GameState gs) {

        int num_actions = actions.length

        if (gs.getGameMode().equals(Types.GAME_MODE.TEAM_RADIO)){
            int[] msg = gs.getMessage();
        }

        ElapsedCpuTimer ect = new ElapsedCpuTimer();
        ect.setMaxTimeMillis(params.num_time);



    }

    public void reset(long seed, int id) {
        super.reset(seed, id);
        randSeed = new Random(seed);

        this.params = (MCTSParams) getParameters()

        if (this.params == null){
            this.params = new MCTSParams();
            super.setParameters(this.params);
        }

    }

    @Override
    public Player copy() {
        return new MCTSPessmisticPlayer(seed, playerID, params);
    }
}
