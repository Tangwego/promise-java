package top.wdcc.promise;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 *
 * @author wavin
 * @date 2022/6/18
 */
public class Promise<O> extends CompletableFuture<O>{

    private CompletableFuture<O> future;

    /**
     * custom thread pool executor
     */
    private final ExecutorService EXECUTOR = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<>(), new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "promise-" + integer.incrementAndGet());
        }
    });

    public Promise(final Callable<O> callable) {
        future = CompletableFuture.supplyAsync(()->{
            CallableFunction<O> callbackFunction = new CallableFunction<>(Thread.currentThread());
            EXECUTOR.submit(()-> {
                try {
                    callable.invoke(callbackFunction);
                } catch (Throwable t) {
                    future.obtrudeException(t);
                }
            });
            return callbackFunction.applyNoArgs();
        }, EXECUTOR);
    }

    /**
     * then function
     * @param action
     * @return
     */
    public Promise<O> then(Consumer<? super O> action) {
        future.thenAccept(action);
        return this;
    }

    /**
     * catch function
     * @param c
     * @return
     */
    public Promise<O> exception(Consumer<Throwable> c) {
        future.exceptionally((t)->{
            c.accept(t);
            return null;
        });
        return this;
    }

    /**
     * finally function
     * @param c
     * @return
     */
    public Promise<O> complete(Consumer<? super O> c) {
        future.whenCompleteAsync((result, t) -> {
            c.accept(result);
            if (t != null) {
                future.obtrudeException(t);
            }
        }, EXECUTOR);
        return this;
    }
}
