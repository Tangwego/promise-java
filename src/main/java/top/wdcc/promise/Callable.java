package top.wdcc.promise;

/**
 * @author wavin
 * @date 2022/6/18
 */
public interface Callable<O> {
    /**
     * invoke resolve or reject method
     * @param resolve
     */
    void invoke(CallableFunction<? extends O> resolve);
}
