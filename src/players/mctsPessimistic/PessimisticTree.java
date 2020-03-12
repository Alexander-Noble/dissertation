package players.mctsPessimistic;


import core.GameState;
import players.mcts.MCTSParams;
import utils.ElapsedCpuTimer;
import players.heuristics.AdvancedHeuristic;
import players.heuristics.CustomHeuristic;
import players.heuristics.StateHeuristic;


public class PessimisticTree {

    public MCTSParams params;
    public int L_depth;


    void PessimisticSearch(ElapsedCpuTimer cpuTimer) {

        while(!stop) {
            GameState state = rootState.copy();
            ElapsedCpuTimer cpuTimerIteration = new ElapsedCpuTimer();

            PessimisticTreeNode selected = new
        }
    }



    void PessimisticSimulation(GameState state, int length, int pessimism_level ) {

        GameState state = rootState.copy();

        for(int i = 0; i < length; i++) {

        }

        for(int i = 0; i < pessimism_level; i++) {
            for
        }


    }
}
