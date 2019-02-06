import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile T result;
    private volatile CustomException exception;

    Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        T localResult = checkTaskResult();
        if (localResult != null) {
            synchronized (this) {
                localResult = checkTaskResult();
                if (localResult != null) {
                    try {
                        result = callable.call();
                    } catch (Exception e) {
                        exception = new CustomException(e.getMessage());
                        throw exception;
                    }
                }
            }
        }
        return localResult;
    }

    private T checkTaskResult() {
        if (result != null) {
            return result;
        }
        if (exception != null) {
            throw exception;
        }
        return null;
    }
}