package tech.huning.treasure.box.async.specs;

/**
 * 异步执行错误
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public interface IAsyncFault {

    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMessage();

}
