class Process {

	  int pid, arrival, burst, remainingTime, completionTime;

	    public Process(int pid, int arrival, int burst) {
	        this.pid = pid;
	        this.arrival = arrival;
	        this.burst = burst;
	        this.remainingTime = burst;
	    }
}
