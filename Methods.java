public class Methods {
    static Partition partition[];

    static void firstFit(String name, int size) { // looks for the first large enough block
        for (int i = 0; i < partition.length; i++) {
            if (partition[i].getPartitionStatus().equals("Free") && partition[i].getPartition() >= size) {
                partition[i].setPartitionStatus("Allocated");
                partition[i].setProcessNum(name);
                partition[i].setProcessSize(size);
                partition[i].calculateFragment();
                return;
            }
        }
        System.out.println("Sorry, there is no free space");
    }


    static void bestFit(String name, int size) { // looking for the smallest block that is large enough
        int bestFit = -1; // still not found

        for (int i = 0; i < partition.length; i++) {
            if (partition[i].getPartitionStatus().equals("Free") && partition[i].getPartition() >= size) {
                if (bestFit == -1 || partition[i].getPartition() < partition[bestFit].getPartition()) {
                    bestFit = i;
                }
            }
        }

        if (bestFit != -1) {
            partition[bestFit].setPartitionStatus("Allocated");
            partition[bestFit].setProcessNum(name);
            partition[bestFit].setProcessSize(size);
            partition[bestFit].calculateFragment();
        } else {
            System.out.println("Sorry, there is no free space");
        }
    }


    static void worstFit(String name, int size) { // looking for largest block available 
        int worsFit = -1;

        for (int i = 0; i < partition.length; i++) {
            if (partition[i].getPartitionStatus().equals("Free") && partition[i].getPartition() >= size) {
                if (worsFit == -1 || partition[i].getPartition() > partition[worsFit].getPartition()) {
                    worsFit = i;
                }
            }
        }

        if (worsFit != -1) {
            partition[worsFit].setPartitionStatus("Allocated");
            partition[worsFit].setProcessNum(name);
            partition[worsFit].setProcessSize(size);
            partition[worsFit].calculateFragment();
        } else {
            System.out.println("Sorry, there is no free space");
        }
    }
}