package pochacm.dto;

public class Status{
    public boolean success;
    public String message;
	@Override
	public String toString() {
		return "Status [success=" + success + ", message=" + message + "]";
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
