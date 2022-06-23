package tech.huning.treasure.box.shell.model;

/**
 * Shell指令执行结果
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class ShellResult {

    // 是否成功
    private boolean isOk;
    // 数据
    private String data;
    // 错误消息
    private String error;

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        this.isOk = ok;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
