package tech.huning.treasure.box.async.specs;

/**
 * 异步调度任务
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public interface IAsyncTask<T> {

    /**
     * 获取任务编码
     * @return 任务编码
     */
    String getId();

    /**
     * 获取任务数据
     * @return 任务数据
     */
    T getData();

}
