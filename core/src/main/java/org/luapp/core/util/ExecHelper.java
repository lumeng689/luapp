package org.luapp.core.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ExecHelper {

    public static ExecHelper exec(String[] cmdArray) throws Exception {
        return new ExecHelper(Runtime.getRuntime().exec(cmdArray), null);
    }

    public static ExecHelper exec(String[] cmdArray, String[] envp) throws Exception {
        return new ExecHelper(Runtime.getRuntime().exec(cmdArray, envp), null);
    }

    public static ExecHelper exec(String[] cmdArray, String charset) throws Exception {
        return new ExecHelper(Runtime.getRuntime().exec(cmdArray), charset);
    }

    public static ExecHelper exec(String[] cmdArray, String[] envp, String charset) throws Exception {
        return new ExecHelper(Runtime.getRuntime().exec(cmdArray, envp), charset);
    }

    public static ExecHelper execUsingShell(String command) throws Exception {
        return execUsingShell(command, null);
    }

    public static ExecHelper execUsingShell(String command, String charset) throws Exception {
        if (command == null)
            throw new NullPointerException();

        String[] cmdArray;

        String os = System.getProperty("os.name");

        if (os.equals("Windows 95") || os.equals("Windows 98") || os.equals("Windows ME")) {
            cmdArray = new String[] { "command.exe", "/C", command };
        } else if (os.startsWith("Windows")) {
            cmdArray = new String[] { "cmd.exe", "/C", command };
        } else {
            cmdArray = new String[] { "/bin/sh", "-c", command };
        }

        return new ExecHelper(Runtime.getRuntime().exec(cmdArray), charset);
    }

    /**
     * The output of the job that ran.
     */
    private String output;

    /**
     * Get the output of the job that ran.
     *
     * @return Everything the executed process wrote to its standard output as a
     *         String.
     */
    public String getOutput() {
        return output;
    }

    /**
     * The error output of the job that ran.
     */
    private String error;

    /**
     * Get the error output of the job that ran.
     *
     * @return Everything the executed process wrote to its standard error as a
     *         String.
     */
    public String getError() {
        return error;
    }

    /**
     * The status of the job that ran.
     */
    private int status;

    /**
     * Get the status of the job that ran.
     *
     * @return exit status of the executed process, by convention, the value 0
     *         indicates normal termination.
     */
    public int getStatus() {
        return status;
    }

    private ExecHelper(Process process, String charset) throws Exception {
        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();

        Reader stdout;
        Reader stderr;

        if (charset == null) {
            stdout = new InputStreamReader(process.getInputStream());
            stderr = new InputStreamReader(process.getErrorStream());
        } else {
            stdout = new InputStreamReader(process.getInputStream(), charset);
            stderr = new InputStreamReader(process.getErrorStream(), charset);
        }

        char[] buf = new char[1024];

        boolean done = false;
        boolean stdoutClosed = false;
        boolean stderrClosed = false;

        while (!done) {
            boolean readSomething = false;

            if (!stdoutClosed && stdout.ready()) {
                readSomething = true;

                int read = stdout.read(buf, 0, buf.length);

                if (read < 0) {
                    readSomething = true;
                    stdoutClosed = true;
                } else if (read > 0) {
                    readSomething = true;
                    output.append(buf, 0, read);
                }
            }

            if (!stderrClosed && stderr.ready()) {
                int read = stderr.read(buf, 0, buf.length);
                if (read < 0) {
                    readSomething = true;
                    stderrClosed = true;
                } else if (read > 0) {
                    readSomething = true;
                    error.append(buf, 0, read);
                }
            }

            if (!readSomething) {
                try {
                    this.status = process.exitValue();
                    done = true;
                } catch (IllegalThreadStateException itx) {
                    // Exit status not ready yet.
                    // Give the process a little breathing room.
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ix) {
                        process.destroy();
                        throw new IOException("Interrupted - processes killed");
                    }
                }
            }
        }

        this.output = output.toString();
        this.error = error.toString();
    }
}
