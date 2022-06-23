package tech.huning.treasure.box.async.specs;

/**
 * 异步调度器配置
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public interface IAsyncConfig {

    /**
     * 获取主题
     * @return 主题信息
     */
    IAsyncTopic getTopic();

    /**
     * 获取任务队列容量
     * @return 任务队列容量
     */
    default int getTaskCapacity() {
        return 200;
    }

    /**
     * 获取执行器容量
     * @return 执行器容量
     */
    default int getExecutorCapacity() {
        return 1;
    }

    /**
     * 获取并发度
     * @return 并发度
     */
    default int getConcurrent() {
        return 2;
    }

    /**
     * 获取线程名前缀
     * @return 线程名前缀
     */
    default String getThreadPrefix(){
        return "async-scheduler-exec-";
    }

    /**
     * 获取线程起始编码
     * @return 线程起始编码
     */
    default int getThreadStartId(){
        return 0;
    }

}
