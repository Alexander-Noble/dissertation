package players.smoothUCT;

import core.GameState;
import players.mcts.MCTSParams;
import players.Player;
import players.optimisers.ParameterizedPlayer;
import utils.ElapsedCpuTimer;
import utils.Types;

import java.util.ArrayList;
import java.util.Random;

public class smoothUCTPlayer extends ParameterizedPlayer {

    private Random rand;
    public MCTSParams params;
    public Types.ACTIONS[] actions;

    public smoothUCTPlayer(long seed, int id) { this(seed, id, new MCTSParams()); }

    public smoothUCTPlayer(long seed, int id, MCTSParams params) {
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
    public void reset(long seed, int id) {
        super.reset(seed, id);
        rand = new Random(seed);
        this.params = (MCTSParams) getParameters();

        if (this.params == null) {
            this.params = new MCTSParams();
            super.setParameters(this.params);
        }

    }

    @Override
    public Types.ACTIONS act(GameState state) {

        
        ElapsedCpuTimer ect = new ElapsedCpuTimer();
        ect.setMaxTimeMillis(params.num_time);

        // Number of actions available
        int num_actions = actions.length;

        // Root of the tree
        smoothNode m_root = new smoothNode(params, rand, num_actions, actions);
        m_root.setRoot(state);

        //Determine the action using MCTS...
        m_root.smoothSearch(ect);

        //Determine the best action to take and return it.
        int action = m_root.mostVisitedAction();

        // TODO update message memory

        //... and return it.
        return actions[action];

    }

    @Override
    public int[] getMessage() {
        // default message
        int[] message = new int[Types.MESSAGE_LENGTH];
        message[0] = 1;
        return message;
    }


    @Override
    public Player copy() { return new smoothUCTPlayer(seed, playerID, params); }
}
