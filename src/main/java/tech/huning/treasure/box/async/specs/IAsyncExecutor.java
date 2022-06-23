package tech.huning.treasure.box.async.specs;

import tech.huning.treasure.box.async.exception.AsyncException;

/**
 * 异步执行器
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public interface IAsyncExecutor<T> {

    /**
     * 执行任务
     * @param task 异步调度任务
     * @throws InterruptedException 中断异常
     */
    default void run(IAsyncTask<T> task) throws InterruptedException {
        try{
            exec(task);
            success(task);
        }catch (AsyncException e) {
            fail(task, e);
        }catch (InterruptedException e) {
            throw e;
        } catch (Exception e) {
            fail(task, new AsyncException(e));
        }
    }

    /**
     * 任务处理
     * @param task 异步调度任务
     * @throws AsyncException 异步调度异常
     * @throws InterruptedException 中断异常
     */
    void exec(IAsyncTask<T> task) throws AsyncException, InterruptedException;

    /**
     * 任务处理失败回调方法
     * @param task 异步调度任务
     * @param exception 异常信息
     */
    default void fail(IAsyncTask<T> task, AsyncException exception) {
    }

    /**
     * 任务处理成功回调方法
     * @param task 异步调度任务
     */
    default void success(IAsyncTask<T> task) {
    }

}
