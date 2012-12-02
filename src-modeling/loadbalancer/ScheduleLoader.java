package loadbalancer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import sun.nio.cs.US_ASCII;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

public class ScheduleLoader {

	public static class TaskParams {
		public BigDecimal cpuUsage = BigDecimal.ZERO;
		public BigDecimal memUsage = BigDecimal.ZERO;
		public BigDecimal connectionsUsage = BigDecimal.ZERO;
		public BigDecimal duration = BigDecimal.ZERO;
	}

	private static final TaskParams NO_MORE_TASKS = new TaskParams();

	/**
	 * Iterates over a file with predefined calls
	 */
	private Iterator<String> iterator;

	public ScheduleLoader(String filePath) {
		try {
			String content = Files.toString(new File(filePath), new US_ASCII());
			String[] rows = content.split("\n");
			List<String> collection = Lists.newArrayList(rows);
			iterator = collection.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TaskParams getNextTaskParams() {
		if (iterator.hasNext()) {
			String next = iterator.next();
			String[] paramsAsStr = next.split(";");
			TaskParams taskParams = new TaskParams();
			taskParams.cpuUsage = new BigDecimal(paramsAsStr[0]);
			taskParams.memUsage = new BigDecimal(paramsAsStr[1]);
			taskParams.connectionsUsage = new BigDecimal(paramsAsStr[2]);
			taskParams.duration = new BigDecimal(paramsAsStr[3].trim());
			return taskParams;
		} else {
			return NO_MORE_TASKS;
		}
		

	}
}
