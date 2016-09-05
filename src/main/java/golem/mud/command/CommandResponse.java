package golem.mud.command;

public class CommandResponse {

    private Object data;

    public CommandResponse(final String data) {
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    public String getStringData() {
        return (this.data == null) ? null : this.data.toString();
    }
}
