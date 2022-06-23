package tech.huning.treasure.box.async.core;

import tech.huning.treasure.box.async.specs.IAsyncConfig;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步调度线程工厂
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public final class AsyncThreadFactory implements ThreadFactory {

    private IAsyncConfig config;
    private AtomicInteger threadId;

    private static AsyncThreadFactory instance;

    private AsyncThreadFactory(IAsyncConfig config){
        this.config = config;
        this.threadId = new AtomicInteger(config.getThreadStartId());
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable,this.config.getThreadPrefix() + this.threadId.incrementAndGet());
        thread.setDaemon(false);
        return thread;
    }

    public static AsyncThreadFactory build(IAsyncConfig config) {
        if(null != instance) {
            return instance;
        }
        synchronized (AsyncThreadFactory.class) {
            instance = new AsyncThreadFactory(config);
        }
        return instance;
    }

}
