package tech.huning.treasure.box.async.specs;

import tech.huning.treasure.box.async.exception.AsyncException;

/**
 * 异步调度器
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public interface IAsyncScheduler {

    /**
     * 获取异步任务调度客户端
     * @param topic 异步调度主题
     * @return 异步任务队列
     * @throws AsyncException 异步任务执行异常
     */
    IAsyncQueue getClient(IAsyncTopic topic) throws AsyncException;

    /**
     * 获取异步任务调度服务端
     * @param config 异步调度配置
     * @return 异步任务队列
     * @throws AsyncException 异步任务执行异常
     */
    IAsyncQueue getServer(IAsyncConfig config) throws AsyncException;

    /**
     * 获取异步任务调度服务端
     * @param topic 异步调度主题
     * @return 异步任务队列
     * @throws AsyncException 异步任务执行异常
     */
    IAsyncQueue getServer(IAsyncTopic topic) throws AsyncException;

}
