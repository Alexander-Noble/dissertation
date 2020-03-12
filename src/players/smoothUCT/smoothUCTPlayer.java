package players.smoothUCT;

import core.GameState;
import players.mcts.MCTSParams;
import players.optimisers.ParameterizedPlayer;
import utils.Types;

public class smoothUCTPlayer extends ParameterizedPlayer {

    private Random randSeed;
    public MCTSParams params;
    public Types.ACTIONS[] actions;

    public void reset(long seed, int id) {
        super.reset(seed, id);
        randSeed = new Random(seed);
        this.params = (MCTSParams) getParameters();

        if (this.params == null) {
            this.params = new MCTSParams();
            super.setParameters(this.params);
        }

    }

    public Types.ACTIONS act(GameState state) {

    }

    public Player copy() { return new smoothUCTPlayer(seed, id, params); }
}
