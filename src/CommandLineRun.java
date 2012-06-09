import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandLineRun {
	Process p;
	Runtime rt;
	BufferedReader stdInput, stdError;
	private boolean running;

	public CommandLineRun(File f, String[] args) {
		rt = Runtime.getRuntime();
		try {
			p = rt.exec(f.getCanonicalPath(), args, f.getCanonicalFile()
					.getParentFile());
			running = true;
		} catch (IOException ex) {
			Logger.getLogger(CommandLineRun.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	}

	public BufferedReader getOutput() {
		return stdInput;
	}

	public BufferedReader getError() {
		return stdError;
	}

	public void halt() {
		p.destroy();
		running = false;
	}

	public boolean procDone() {
		try {
			int v = p.exitValue();
			running = false;
			return true;
		} catch (IllegalThreadStateException e) {
			return false;
		}
	}

	public boolean isRunning() {
		return running;
	}
}