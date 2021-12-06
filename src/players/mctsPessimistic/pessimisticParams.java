package players.mctsPessimistic;

import players.optimisers.ParameterSet;
import utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class pessimisticParams implements ParameterSet {

    // Constants
    public final double HUGE_NEGATIVE = -1000;
    public final double HUGE_POSITIVE =  1000;

    public final int STOP_TIME = 0;
    public final int STOP_ITERATIONS = 1;
    public final int STOP_FMCALLS = 2;

    public final int CUSTOM_HEURISTIC = 0;
    public final int ADVANCED_HEURISTIC = 0;
    public final int PESSIMISTIC_HEURISTIC = 1;

    public double epsilon = 1e-6;

    // Parameters
    public double K = Math.sqrt(2);
    public int rollout_depth = 8;
    public int pessimistic_depth = 1;//10;
    public int heuristic_method = PESSIMISTIC_HEURISTIC;
    public int pessimistic_length = 12;
    public int pessimism = 5;

    // Budget settings
    public int stop_type = STOP_TIME;
    public int num_iterations = 200;
    public int num_fmcalls = 2000; // Use for testing
    public int num_time = 40;

    @Override
    public void setParameterValue(String param, Object value) {
        switch(param) {
            case "K": K = (double) value; break;
            case "pessimistic_depth": pessimistic_depth = (int) value; break;
            case "heuristic_method": heuristic_method = (int) value; break;
            case "pessimistic_length": pessimistic_length = (int) value; break;
            case "pessimism": pessimism = (int) value; break;
            case "rollout_depth": rollout_depth = (int) value; break;

        }
    }

    @Override
    public Object getParameterValue(String param) {
        switch(param) {
            case "K": return K;
            case "pessimistic_depth": return pessimistic_depth;
            case "heuristic_method": return heuristic_method;
            case "pessimistic_length": return pessimistic_length;
            case "pessimism": return pessimism;
            case "rollout_depth": return rollout_depth;
        }
        return null;
    }

    @Override
    public ArrayList<String> getParameters() {
        ArrayList<String> paramList = new ArrayList<>();
        paramList.add("K");
        paramList.add("pessimistic_depth");
        paramList.add("heuristic_method");
        paramList.add("pessimistic_length");
        paramList.add("pessimism");
        paramList.add("rollout_depth");

        return paramList;
    }

    @Override
    public Map<String, Object[]> getParameterValues() {
        HashMap<String, Object[]> parameterValues = new HashMap<>();
        parameterValues.put("K", new Double[]{1.0, Math.sqrt(2), 2.0});
        parameterValues.put("pessimistic_depth", new Integer[]{5, 8, 10, 12, 15});
        parameterValues.put("heuristic_method", new Integer[]{CUSTOM_HEURISTIC, ADVANCED_HEURISTIC});
        parameterValues.put("pessimistic_length", new Integer[]{2, 4, 6, 8, 10});
        parameterValues.put("pessimism", new Integer[]{2, 4, 8, 10, 16});
        parameterValues.put("rollout_depth", new Integer[]{5, 8, 10, 12, 15});
        return parameterValues;
    }

    @Override
    public Pair<String, ArrayList<Object>> getParameterParent(String parameter) {
        return null;  // No parameter dependencies
    }

    @Override
    public Map<Object, ArrayList<String>> getParameterChildren(String root) {
        return new HashMap<>();  // No parameter dependencies
    }

    @Override
    public Map<String, String[]> constantNames() {
        HashMap<String, String[]> names = new HashMap<>();
        names.put("heuristic_method", new String[]{"CUSTOM_HEURISTIC", "ADVANCED_HEURISTIC"});
        return names;
    }
}

