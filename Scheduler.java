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
        ganttChart.append("Time\t\tProcess/CS/Idle\n");
        QueueSize = processes.size();
    }

    public void execute() {
        Process lastProcess = null; // keeps track of previous process for CS detection

        while ( processes.size() != 0 ) {//loops until all processes complete 

            Process shortest = findShortestRemainingProcess();//retrieve prioritized process

            if (shortest == null) { //no process arrived  
                ganttChart.append(currentTime).append("\t\tIdle\n");
                idleTime++;
            } else {
                if (lastProcess != null && lastProcess != shortest) { //new process in
                    ganttChart.append(currentTime).append("\t\tCS\n");//context switch
                    currentTime++; 
                    idleTime++;
                }

                ganttChart.append(currentTime).append("\t\tP").append(shortest.pid).append("\n");
                shortest.remainingTime--;
                
                if (shortest.remainingTime == 0) { //when process is completed
                    shortest.completionTime = currentTime + 1;
                    int turnaroundTime = shortest.completionTime - shortest.arrival;
                    int waitingTime = turnaroundTime - shortest.burst;
                    
                    //accumulate a process values to the sums
                    turnaroundTimeSum += turnaroundTime; 
                    waitingTimeSum += waitingTime;
                    processes.remove(shortest);//remove it from queue
                }

                lastProcess = shortest;
            }

            currentTime++; 
        }
    }

    private Process findShortestRemainingProcess() { //method to pick a process with the highest priority(lowest remaining burst time)
        Process shortest = null;
        for (Process p : processes) { //for each process
            if (p.arrival <= currentTime) {// it has arrived already
            	if (shortest == null || p.remainingTime <= shortest.remainingTime ||//pick the shortest of them all
            	(p.remainingTime == shortest.remainingTime && p.arrival < shortest.arrival))//handle FCFS case
                    shortest = p;     
            }
        }
        return shortest;
    }

    public void printReport() {
        System.out.println("Gantt Chart:");
        System.out.println(ganttChart);     
        System.out.println("\nPerformance Metrics:");
        System.out.println("Average Turnaround Time: " + String.format("%.2f", (double) turnaroundTimeSum / QueueSize));
        System.out.println("Average Waiting Time: " + String.format("%.2f", (double) waitingTimeSum /QueueSize));
        System.out.println("CPU Utilization: " + String.format("%.2f", ((double) (currentTime - idleTime) / currentTime) * 100) + "%");
    }
}
