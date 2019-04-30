package common;

import javax.servlet.http.HttpServletResponse;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -1835206418558868797L;
    private String errorMsg;
    private int errorCode = HttpServletResponse.SC_BAD_REQUEST;

    public CustomException() {
        super();
    }

    public CustomException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public CustomException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
