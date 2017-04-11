package net.soumoney.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * execute nativate command avaiable for win9x,winnt ,win2k,unix-like os
 * 
 */

public class ExecShellCmd extends Thread {
	public static void main(String[] args) {
		System.out.println(exec("pdf2swf"));
	}
	private final static boolean bDebug = true;
	public static final String WINDOWS_7 = "WINDOWS 7";
	public static final String WINDOWS_XP = "WINDOWS XP";
	public static final String WINDOWS_95 = "WINDOWS 95";
	public static final String WINDOWS_98 = "WINDOWS 98";
	public static final String WINDOWS_NT = "WINDOWS NT";
	public static final String WINDOWS_2K = "WINDOWS 2000";
	public static final String LINUX = "LINUX";
	public static final String SOLARIS = "SOLARIS";
	public static final String AIX = "AIX";
	public static final String FREEBSD = "FREEBSD";
	public static final String IRIX = "IRIX";
	public static final String HP_UX = "HP-UX";

	/**
	 * execute shell command according to OS
	 * 
	 * @param sCmd
	 *            command list
	 * @return echomessage
	 */
	public static String exec(String[] sCmd) {
		System.out.println("execute commands:");
		for (int i = 0; i < sCmd.length; i++) {
			System.out.println(sCmd[i]);
		}
		String strRet = "";
		String sOS = System.getProperty("os.name").toUpperCase();
		if (sOS.equals(WINDOWS_2K) || sOS.equals(WINDOWS_NT) || sOS.equals(WINDOWS_7) || sOS.equals(WINDOWS_XP))
			strRet = execInWinNT(sCmd);
		if (sOS.equals(WINDOWS_95) || sOS.equals(WINDOWS_98))
			strRet = execInWin9x(sCmd);
		if (sOS.equals(LINUX) || sOS.equals(FREEBSD) || sOS.equals(AIX)
				|| sOS.equals(IRIX) || sOS.equals(SOLARIS) || sOS.equals(HP_UX))
			strRet = execInUNIX(sCmd);
		return strRet;
	}

	public static String exec(String sCmd) {
		String[] sCmds = new String[1];
		sCmds[0] = sCmd;
		return exec(sCmds);
	}
	
	/**
	 * win9x
	 */
	private static String execInWin9x(String sCmd[]) {
		String strRet = "";
		try {
			Runtime aRT = Runtime.getRuntime(); // Runtime.getRuntime();
			/* Spawn a shell sub-process */
			Process aProc = aRT.exec("command.com");
			strRet = exec(aProc, sCmd);
		} catch (IOException ex) {
			if (bDebug)
				System.out.println("ExeShellCmd.execInWin9x:error");
		}
		return strRet;
	}

	/**
	 * winNT, win2000
	 */
	private static String execInWinNT(String sCmd[]) {
		String strRet = "";
		try {
			Runtime aRT = Runtime.getRuntime(); // Runtime.getRuntime();
			/* Spawn a shell sub-process */
			Process aProc = aRT.exec("cmd.exe");
			strRet = exec(aProc, sCmd);
		} catch (IOException ex) {
			if (bDebug)
				System.out.println("ExeShellCmd.execInWinNT:error");
		}
		return strRet;
	}

	/**
	 * UNIX
	 */
	private static String execInUNIX(String sCmd[]) {
		String strRet = "";
		try {
			Runtime aRT = Runtime.getRuntime(); // Runtime.getRuntime();
			/* Spawn a shell sub-process */
			Process aProc = aRT.exec("bash");
			strRet = exec(aProc, sCmd);
		} catch (IOException ex) {
			if (bDebug)
				System.out.println("ExeShellCmd.execInUNIX:error"
						+ ex.getMessage() + "<br>");
		}
		return strRet;
	}

	/**
	 * General Exe method
	 */
	private static String exec(Process aProc, String sCmd[]) {
		String strRet = "";
		try {
			strRet = "";
			StreamGobbler errGrobbler = new StreamGobbler(aProc
					.getErrorStream(), "ERROR");
			errGrobbler.start();
			System.out.println("ExeShellCmd.cmd : error "
					+ errGrobbler.getLog());
			StreamGobbler outputGrobbler = new StreamGobbler(aProc
					.getInputStream(), "OUTPUT");
			outputGrobbler.start();
			PrintWriter shell = new PrintWriter(aProc.getOutputStream());
			for (int i = 0; i < sCmd.length; i++) {
				if (bDebug) {
					System.out.println("ExeShellCmd.cmd : output " + sCmd[i]);
				}
				shell.println(sCmd[i]);
				shell.flush();
			}
			shell.close();

			int exitVal = aProc.waitFor();
			String strErr = errGrobbler.getLog();
			String strOut = outputGrobbler.getLog();
			if ((strErr != null) && !(strErr.equals(""))) {
				strRet += strErr;
			}
			if ((strOut != null) && !(strOut.equals(""))) {
				strRet += strOut;
			}
			if (bDebug) {
				System.out.println("ExitValue : " + exitVal);
			}
		} catch (Exception ioex) {
			if (bDebug) {
				System.out.println("ExeShellCmd: io error");
			}
		}
		return strRet;
	}
}

/**
 * Description: get process input stream information
 * 
 * @version 1.0
 * 
 */

class StreamGobbler extends Thread {
	InputStream is;
	String type;
	String sInput;
	String sLog;

	StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.type = type;
		this.sInput = "";
		this.sLog = "";
	}

	public String getInput() {
		return sInput;
	}

	public String getLog() {
		return sLog;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				sLog += type + ">" + line + "<br>";
				sInput += line;
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
