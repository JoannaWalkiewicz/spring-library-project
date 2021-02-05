package pl.pjatk.library.model;

public class Response<T> {
    private String errorMessage;
    private T result;

    public Response(String errorMessage, T result) {
        this.errorMessage = errorMessage == null ? "" : errorMessage;
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T getResult() {
        return result;
    }
}