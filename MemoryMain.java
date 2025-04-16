import java.util.Scanner;

public class MemoryMain {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of memory blocks: ");
        int num = input.nextInt();
        Partition memory[] = new Partition[num];
        Methods.partition = memory;

        int start = 0, end;
        for (int i = 0; i < num; i++) {
            System.out.print("Enter the size of memory block " + (i + 1) + ": ");
            int size = input.nextInt();
            end = start + size - 1;
            memory[i] = new Partition(start, end, size);
            start = end + 1;
        }

        while (true) {
            System.out.println("Choose action:\n1-Allocate Memory\n2-Deallocate Memory\n3-Print Performance Analysis\n4-Exit");
            int action = input.nextInt();

            switch (action) {
                case 1:
                    System.out.print("Enter process name: ");
                    String name = input.next();
                    System.out.print("Enter process size: ");
                    int size = input.nextInt();
                    System.out.println("Choose allocation strategy:\n1-First Fit\n2-Best Fit\n3-Worst Fit");
                    int choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            Methods.firstFit(name, size);
                            break;
                        case 2:
                            Methods.bestFit(name, size);
                            break;
                        case 3:
                            Methods.worstFit(name, size);
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                    break;
                case 2:
                    System.out.print("Enter process name to deallocate: ");
                    String pName = input.next();
                    release(pName, memory);
                case 3:
                    report(memory);
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    input.close();
                    System.exit(0);
            }
        }
    }

    static void release(String processName, Partition[] partition) {
        boolean found = false;
    
        for (int i = 0; i < partition.length; i++) { // look for the process in the partitions
            if (partition[i].getProcessNum().equals(processName)) { // if found, deallocate it
                partition[i].setPartitionStatus("Free");
                partition[i].setProcessNum("Null");
                partition[i].setProcessSize(0);
                partition[i].setFragmentSize(-1);
                found = true;
                System.out.println("Process " + processName + " released from memory.");
            }
        }
    
        if (!found) {
            System.out.println("Process " + processName + " not found in any partition.");
        }
    }
    
    static void report(Partition[] memory) {
        System.out.println("=====================================================================================");
        System.out.println("Block#   Size (KB)   Start-End Address      Status       Process ID   Fragmentation");
        System.out.println("=====================================================================================");
    
        for (int i = 0; i < memory.length; i++) {
            Partition p = memory[i];
            System.out.printf("  %-7d%-12d%-21s%-13s%-13s%d\n",
                    i,
                    p.getPartition(),
                    p.getStartAddress() + "-" + p.getEndAddress(),
                    p.getPartitionStatus(),
                    p.getProcessNum(),
                    p.getFragmentSize());
        }
    
        System.out.println("=====================================================================================");
    }
}