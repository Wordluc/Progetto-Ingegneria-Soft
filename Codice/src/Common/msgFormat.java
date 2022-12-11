package Common;

import java.io.Serializable;
import java.util.ArrayList;

abstract class msgFormat implements Serializable {

    private String command;
    private ArrayList<String> option;
    private String separator;
    private String startWith;
    private String message;
    private Object obj;
    private ArrayList<Object> objects;

    public msgFormat(String msg) {
        this(msg, "!", " ");
    }

    public msgFormat(String msg, String startWith, String separator) {
        this.separator = separator;
        this.startWith = startWith;
        message = "";
        option = new ArrayList<>(); //--- In futuro
        generateCommand(msg);

    }

    public String getCommand() {
        return command;
    }

    public String getOption(int pos) throws Exception {

        if (pos < 0 || pos >= option.size())
            throw new Exception("Parametro mancante");
        else
            return option.get(pos);

    }

    private void generateCommand(String text) {
        String[] help;
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
    }

    public String getMessage() {
        return message;
    }

    public void setObj(ArrayList<Object> obj) {
        this.objects = obj;
    }

    public ArrayList<Object> getArrayObj() {
        return objects;
    }

    public void setArrayObj(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    @Override
    public String toString() {
        String text = command;
        for (String o : option)
            text += o + " " + message;
        return text;
    }
}
