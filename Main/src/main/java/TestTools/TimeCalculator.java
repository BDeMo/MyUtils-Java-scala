package TestTools;

/**
 * Tool for Timetaking testing.
 */
public class TimeCalculator{
    /**
     * Calculating timetaking.
     * Lambda usable.
     * @param actionHelper  overrided {@code ActionHelper.Action()}
     * @return timetaking of Action.
     */
    public static long Calculate(ActionHelper actionHelper) {
        long time = 0;
        time = System.currentTimeMillis();
        actionHelper.Action();
        time = System.currentTimeMillis() - time;
        return time;
    }
}
