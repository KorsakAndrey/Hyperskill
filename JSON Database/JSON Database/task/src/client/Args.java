package client;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;

public class Args {
    @Parameter(names = "-t", description = "Type")
    private String type;

    @Parameter(names = "-k", description = "Key")
    private String key;

    @Parameter(names = "-v", description = "Value")
    private String value;

    @Parameter(names = "-in", description = "Input file")
    private String file;

    public String getFile() {

        return file;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
