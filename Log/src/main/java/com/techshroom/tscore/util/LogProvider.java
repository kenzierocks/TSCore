package com.techshroom.tscore.util;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Logger;

public final class LogProvider {

	private LogProvider() {
	}

	private static final Logger bkupLog = Logger.getLogger("TSCore-backuplog");

	/**
	 * Original streams from start of program
	 */
	public static final PrintStream STDOUT = System.out, STDERR = System.err;

	public static final PrintStream METHODIZEDOUT, METHODIZEDERR;

	static {
		STDOUT.print("Methodizing STD streams...");
		METHODIZEDOUT = new MethodizedSTDStream(STDOUT).asPrintStream();
		METHODIZEDERR = new MethodizedSTDStream(STDERR).asPrintStream();
		STDOUT.println("complete.");
		METHODIZEDOUT.print("Setting System.out...");
		System.setOut(METHODIZEDOUT);
		METHODIZEDOUT.println("complete.");
		METHODIZEDERR.print("Setting System.err...");
		System.setErr(METHODIZEDERR);
		METHODIZEDERR.println("complete.");
	}

	public static Logger LOG = bkupLog;

	/**
	 * A dummy method to load this class. Does nothing.
	 */
	public static void init() {

	}

	/**
	 * <p>
	 * These logging groups are used to filter certain information. The default
	 * logging combo is INFO + WARNING + ERROR.
	 * </p>
	 * 
	 * Logging groups from lowest to highest: INFO, WARNING, DEBUG, JUNK.<br>
	 * <br>
	 * 
	 * Recommended usages:
	 * <dl>
	 * <dt>INFO</dt>
	 * <dd>- STDOUT</dd>
	 * <dt>WARNING</dt>
	 * <dd>- warnings like non-fatal OpenGL errors</dd>
	 * <dt>ERROR</dt>
	 * <dd>- STDERR</dd>
	 * <dt>DEBUG</dt>
	 * <dd>- debug info for developing</dd>
	 * <dt>JUNK</dt>
	 * <dd>- for batch-dumping information</dd>
	 * </dl>
	 */
	public static enum LoggingGroup {
		/**
		 * Standard output for users; etc.
		 */
		INFO,
		/**
		 * Non-fatal errors or suggestions for performance
		 */
		WARNING,
		/**
		 * Fatal errors
		 */
		ERROR,
		/**
		 * Debug output for developing
		 */
		DEBUG,
		/**
		 * Dump group for unloading tons of data
		 */
		JUNK;

		public static final EnumSet<LoggingGroup> ALL = EnumSet
				.allOf(LoggingGroup.class);
	}

	private static Set<LoggingGroup> logGroups = EnumSet.of(LoggingGroup.INFO,
			LoggingGroup.WARNING, LoggingGroup.ERROR);

	public static Set<LoggingGroup> getValidGroups() {
		return EnumSet.copyOf(logGroups);
	}

	public static Set<LoggingGroup> setValidGroups(Set<LoggingGroup> groups) {
		logGroups = EnumSet.copyOf(groups);
		return getValidGroups();
	}

	public static Set<LoggingGroup> setValidGroups(LoggingGroup... groups) {
		return setValidGroups(EnumSet.copyOf(Arrays.asList(groups)));
	}

	public static boolean isValidGroup(LoggingGroup g) {
		return getValidGroups().contains(g);
	}
}
