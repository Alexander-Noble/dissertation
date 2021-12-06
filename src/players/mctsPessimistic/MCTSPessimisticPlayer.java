package players.mctsPessimistic;

import core.GameState;
import players.mctsPessimistic.PessimisticTree;
import players.mctsPessimistic.pessimisticParams;
import players.optimisers.ParameterizedPlayer;
import utils.ElapsedCpuTimer;
import utils.Types;

import java.util.ArrayList;
import java.util.Random;

public class MCTSPessimisticPlayer extends ParameterizedPlayer {

    public pessimisticParams params;

    private Random randSeed;

    public  Types.ACTIONS[] actions;

    public MCTSPessimisticPlayer(long seed, int id) { this(seed, id, new pessimisticParams()); }


    public MCTSPessimisticPlayer(long seed, int id, pessimisticParams params) {
        super(seed, id, params);
        reset(seed, id);

        ArrayList<Types.ACTIONS> actionsList = Types.ACTIONS.all();
        actions = new Types.ACTIONS[actionsList.size()];
        int i = 0;
        for (Types.ACTIONS act : actionsList) {
            actions[i++] = act;
        }
    }


    @Override
    public Types.ACTIONS act(GameState gs) {

        // TODO update gs
        if (gs.getGameMode().equals(Types.GAME_MODE.TEAM_RADIO)){
            int[] msg = gs.getMessage();
        }

        ElapsedCpuTimer ect = new ElapsedCpuTimer();
        ect.setMaxTimeMillis(params.num_time);

        // Number of actions available
        int num_actions = actions.length;

        // Root of the tree
        PessimisticTree m_root = new PessimisticTree(params, randSeed, num_actions, actions);
        m_root.setRootGameState(gs);

        //Determine the action using MCTS...
        m_root.mctsSearch(ect);

        //Determine the best action to take and return it.
        int action = m_root.mostVisitedAction();

        // TODO update message memory

        //... and return it.
        return actions[action];
    }


    public void reset(long seed, int id) {
        super.reset(seed, id);
        randSeed = new Random(seed);

        this.params = (pessimisticParams) getParameters();

        if (this.params == null){
            this.params = new pessimisticParams();
            super.setParameters(this.params);
        }

    }

    @Override
    public int[] getMessage() {
        // default message
        int[] message = new int[Types.MESSAGE_LENGTH];
        message[0] = 1;
        return message;
    }

    @Override
    public MCTSPessimisticPlayer copy() {
        return new MCTSPessimisticPlayer(seed, playerID, params);
    }
}
