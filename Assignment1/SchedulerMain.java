import java.util.*;

public class SchedulerMain {
	
	 public static void main(String[] args) {
		 
	        Scanner input = new Scanner(System.in);
	        System.out.print("Number of processes= ");
	        int n = input.nextInt();
	        System.out.println("Arrival times and burst times as follows:");
	        List<Process> processes = new ArrayList<>();
	        
	        int pid, arrival, burst;
	        for (int i = 0; i < n; i++) {
	            System.out.println("P" + (i + 1) + ": ");
	            pid = i + 1;
	            System.out.print("Arrival time = ");
	            arrival = input.nextInt();
	            System.out.print("Burst time = ");
	            burst = input.nextInt();
	            Process p = new Process(pid, arrival, burst);
	            processes.add(p);
	        }

	        Scheduler s = new Scheduler(processes);
	        s.execute();
	        s.printReport();
	        input.close();
	    }

}
