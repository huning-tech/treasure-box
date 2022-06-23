package tech.huning.treasure.box.async.specs;

import java.util.List;

/**
 * 异步调度待处理任务列表
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface IAsyncStock {

    /**
     * 获取任务数量
     * @return 任务数量
     */
    int getTotal();

    /**
     * 获取任务列表
     * @return 任务列表
     */
    List<IAsyncTask> getList();

}
