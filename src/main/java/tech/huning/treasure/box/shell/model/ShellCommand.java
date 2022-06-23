package tech.huning.treasure.box.shell.model;

/**
 * Shell指令
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class ShellCommand {

    // 命令行
    private String line;

    public ShellCommand(){}

    public ShellCommand(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

}
