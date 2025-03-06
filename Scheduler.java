import java.util.*;

class Scheduler {
    
    private List<Process> processes;  
    private int currentTime = 0; //current time
    private int idleTime = 0; //time spent being idle or context switching
    private int turnaroundTimeSum = 0, waitingTimeSum = 0; //sum of performance metrics 
    private StringBuilder ganttChart = new StringBuilder();
    private int QueueSize;

    public Scheduler(List<Process> processes) { //constructor
        this.processes = new ArrayList<>(processes);
        ganttChart.append("Time\t\tProcess/CS\n");
        QueueSize = processes.size();
    }

    public void execute() {
        Process lastProcess = null; // keeps track of previous process for CS detection
        int intervalStart=0;    // a helper variable for printing time intervals

        
        while ( processes.size() != 0 ) {//loops until all processes complete 
        	
            Process shortest = findShortestRemainingProcess();//retrieve prioritized process
            	
                if (lastProcess != null && lastProcess != shortest) { //new process in (context switching)
                	
                	//store the time interval for both finished process burst and context switch
                	ganttChart.append(intervalStart + "-" + currentTime ).append("\t\tP").append(lastProcess.pid).append("\n");
                    ganttChart.append(currentTime + "-" + ( currentTime+1) ).append("\t\tCS\n");
                    currentTime++; //increment 1ms for CS
                    idleTime++; //increment idle time
                    intervalStart = currentTime; //update intervalStartTime for the next process
                }
            	
                shortest.remainingTime--; //decrement current process remaining time
                
                if (shortest.remainingTime == 0) { //when process is completed
                    shortest.completionTime = currentTime + 1;
                    int turnaroundTime = shortest.completionTime - shortest.arrival;
                    int waitingTime = turnaroundTime - shortest.burst;
                    
                    //accumulate a process values to the sums
                    turnaroundTimeSum += turnaroundTime; 
                    waitingTimeSum += waitingTime;
                    processes.remove(shortest);//remove it from queue
                }

            lastProcess = shortest; //stores current process for the next iteration needs
            currentTime++; //current time will increment in every iteration 
        }
        
        if (lastProcess != null)  //appends the last process interval to be printed
            ganttChart.append(intervalStart + "-" + currentTime).append("\t\tP").append(lastProcess.pid).append("\n");
          
    }

    private Process findShortestRemainingProcess() { //method to pick a process with the highest priority(lowest remaining burst time)
        Process shortest = null;
        for (Process p : processes) { //for each process
            if (p.arrival <= currentTime) {// that has arrived already
            	if (shortest == null || p.remainingTime < shortest.remainingTime ||//pick the shortest of them all
            	(p.remainingTime == shortest.remainingTime && p.arrival < shortest.arrival))//handle FCFS case (equal bursts case)
                    shortest = p;     
            }
        }
        return shortest;
    }

    public void printReport() {
        System.out.println("Scheduling Algorithm: Shortest remaining time first");
        System.out.println("Context Switch: 1ms");
        System.out.println(ganttChart);     
        System.out.println("\nPerformance Metrics:");
        System.out.println("Average Turnaround Time: " + String.format("%.2f", (double) turnaroundTimeSum / QueueSize));
        System.out.println("Average Waiting Time: " + String.format("%.2f", (double) waitingTimeSum /QueueSize));
        System.out.println("CPU Utilization: " + String.format("%.2f", ((double) (currentTime - idleTime) / currentTime) * 100) + "%");
    }
}