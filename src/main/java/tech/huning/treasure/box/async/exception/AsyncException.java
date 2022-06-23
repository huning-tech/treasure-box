package tech.huning.treasure.box.async.exception;

import tech.huning.treasure.box.async.specs.IAsyncFault;
import tech.huning.treasure.box.async.core.AsyncFault;

/**
 * 异步调度异常
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class AsyncException extends Exception {

    private final IAsyncFault asyncFault;

    public AsyncException(IAsyncFault resultCode) {
        this(resultCode, null);
    }

    public AsyncException(Throwable cause) {
        this(AsyncFault.SERVICE_EXCEPTION, cause);
    }

    public AsyncException(IAsyncFault asyncFault, Throwable cause) {
        super(asyncFault.getMessage(), cause);
        this.asyncFault = asyncFault;
    }

    public IAsyncFault getAsyncFault() {
        return asyncFault;
    }

}
