package tech.huning.treasure.box.async.specs;

import tech.huning.treasure.box.async.exception.AsyncException;

/**
 * 异步调度溢出处理器
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface IAsyncOverflowHandler {

    /**
     * 异步调度溢出处理
     * @param task 异步调度任务
     * @throws AsyncException 异步执行异常
     */
    void handle(IAsyncTask task) throws AsyncException;

}
