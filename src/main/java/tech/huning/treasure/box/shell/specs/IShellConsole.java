package tech.huning.treasure.box.shell.specs;

import tech.huning.treasure.box.shell.exception.ShellException;
import tech.huning.treasure.box.shell.model.ShellCommand;
import tech.huning.treasure.box.shell.model.ShellResult;

/**
 * Shell执行器
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public interface IShellConsole {

    /**
     * 执行指令
     * @param cmd 指令
     * @return 结果
     * @throws ShellException 执行异常
     */
    ShellResult exec(ShellCommand cmd) throws ShellException;

}
