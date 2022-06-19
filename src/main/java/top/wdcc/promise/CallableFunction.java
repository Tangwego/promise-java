package top.wdcc.promise;

import java.util.concurrent.locks.LockSupport;
import java.util.function.Function;

/**
 * @author wavin
 * @date 2022/6/19
 */
public class CallableFunction<O> implements Function<Object,O> {

    private O o;

    private Thread execThread;

    public CallableFunction(Thread thread) {
        this.execThread = thread;
    }

    @Override
    public O apply(Object i) {
        LockSupport.park(this.execThread);
        return this.o;
    }

    public O applyNoArgs() {
        return apply(null);
    }

    public void resolve(O o) {
        this.o = o;
        LockSupport.unpark(this.execThread);
    }

    public void reject() {
        this.o = null;
        LockSupport.unpark(this.execThread);
    }
}
