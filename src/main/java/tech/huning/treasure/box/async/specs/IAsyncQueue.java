package tech.huning.treasure.box.async.specs;

import tech.huning.treasure.box.async.exception.AsyncException;

/**
 * 异步调度任务处理队列
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface IAsyncQueue {

    /**
     * 异步任务调度
     * @param task 异步任务
     * @return 异步任务队列
     * @throws AsyncException 异步执行异常
     */
    IAsyncQueue schedule(IAsyncTask task) throws AsyncException;

    /**
     * 异步执行器注册
     * @param executor 执行器
     * @return 异步任务队列
     * @throws AsyncException 异步执行异常
     */
    IAsyncQueue register(IAsyncExecutor executor) throws AsyncException;

    /**
     * 溢出处理器注册
     * @param handler 溢出处理器
     * @return 异步任务队列
     * @throws AsyncException 异步执行异常
     */
    IAsyncQueue register(IAsyncOverflowHandler handler) throws AsyncException;

    /**
     * 获取待处理的任务列表
     * @return 待处理的任务列表
     * @throws AsyncException 异步执行异常
     */
    IAsyncStock getStock() throws AsyncException;

    /**
     * 获取处理中的任务列表
     * @return 处理中的任务列表
     * @throws AsyncException 异步执行异常
     */
    IAsyncHandling getHandling() throws AsyncException;

    /**
     * 监听任务队列
     * @throws AsyncException 异步执行异常
     */
    void listen() throws AsyncException;

    /**
     * 关闭调度器
     */
    void shutdown();

}
