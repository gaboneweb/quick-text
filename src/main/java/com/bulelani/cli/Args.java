package com.bulelani.cli;

import java.util.Arrays;

public class Args {

    private final String command;
    private final String[] parameters;

    private Args(String command, String[] parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public static Args parse(String[] rawArgs) {
        if (rawArgs == null || rawArgs.length == 0) {
            return new Args("help", new String[]{});
        }

        String command = rawArgs[0].toLowerCase();
        String[] parameters = Arrays.copyOfRange(rawArgs, 1, rawArgs.length);

        return new Args(command, parameters);
    }

    public String getCommand() {
        return command;
    }

    public String[] getParameters() {
        return parameters;
    }

    public String getParameter(int index) {
        if (index < 0 || index >= parameters.length) {
            throw new IndexOutOfBoundsException(
                    "Parameter index " + index + " requested but only " + parameters.length + " parameters provided."
            );
        }
        return parameters[index];
    }

    public boolean hasParameter(int index) {
        return index >= 0 && index < parameters.length;
    }

    public int parameterCount() {
        return parameters.length;
    }

    @Override
    public String toString() {
        return "Args{command='" + command + "', parameters=" + Arrays.toString(parameters) + "}";
    }
}
