package Common;

import java.io.Serializable;
import java.util.ArrayList;

abstract class MsgFormat implements Serializable {

    private String command;
    private ArrayList<String> option;
    private String separator;
    private String startWith;
    private String message;
    private Object obj;


    public MsgFormat(String msg) {
        this(msg, "!", " ");
    }

    public MsgFormat(String msg, String startWith, String separator) {
        this.separator = separator;
        this.startWith = startWith;
        message = "";
        option = new ArrayList<>();
        generateCommand(msg);


    }

    public static <T extends Object> T cast(Object obj) {
        return (T) obj;
    }

    public MsgFormat() {
        this(null, null, null);
    }


    public String getOption(int pos) throws Exception {

        if (pos < 0 || pos >= option.size())
            throw new Exception("Parametro mancante");
        else
            return option.get(pos);

    }

    private void generateCommand(String text) {
        String[] help;
        if (text != null) {
            if (text.startsWith(startWith)) {
                help = text.split(separator);
                command = help[0].substring(1);
                if (help.length > 1) {
                    for (int i = 1; i < help.length; i++)
                        option.add(help[i]);
                }
            } else {
                command = "message";
                message = text;
            }
        } else {
            command = "";
            message = "";

        }
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        message = msg;
    }


    public void setArrayObj(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    @Override
    public String toString() {
        String text = "!" + command + " ";
        if (option.size() >= 1) {
            for (String o : option)
                text += o + " " + message + " ";
        } else
            text += message;
        return text;
    }
}
