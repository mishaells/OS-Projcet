import java.util.*;

public class Scheduler {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Number of processes= ");
        int n = input.nextInt();
        System.out.println("Arrival times and burst times as follows:");
        PriorityQueue<Process> processes = new PriorityQueue<>(Comparator.comparingInt(p -> p.arrival));
        
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

        schedule(processes);
        input.close();
    }

    public static void schedule(PriorityQueue<Process> processes) {
        // incomplete
    }
}
class Process {
    int pid, arrival, burst, remainingTime, completionTime, turnaroundTime, waitingTime;

    public Process(int pid, int arrival, int burst) {
        this.pid = pid;
        this.arrival = arrival;
        this.burst = burst;
        this.remainingTime = burst;
    }
}