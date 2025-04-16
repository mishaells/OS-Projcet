public class Partition {
	private int startAddress;
	private int endAddress;
	private int fragmentSize;
	private int partition;
	private String partitionStatus;
	private String processNum;
	private int processSize;

	public Partition(int startAddress, int endAddress, int partition) {
		this.startAddress = startAddress;
		this.endAddress = endAddress;
		this.partition = partition;
		this.fragmentSize = -1;

		this.partitionStatus = "Free";
		this.processNum = "Null";
	}


    public void calculateFragment() {
		if (partitionStatus.equals("Allocated") && !processNum.equals("Null")) {
			fragmentSize = partition - processSize;
		} else {
			fragmentSize = -1;
		}
	}

	public int getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(int startAddress) {
		this.startAddress = startAddress;
	}

	public int getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(int endAddress) {
		this.endAddress = endAddress;
	}

	public int getFragmentSize() {
		return fragmentSize;
	}

	public void setFragmentSize(int fragmentSize) {
		this.fragmentSize = fragmentSize;
	}

	public int getPartition() {
		return partition;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}

	public String getPartitionStatus() {
		return partitionStatus;
	}

	public void setPartitionStatus(String partitionStatus) {
		this.partitionStatus = partitionStatus;
	}

	public String getProcessNum()  {
		return processNum;
	}

	public void setProcessNum(String processNum) {
		this.processNum = processNum;
	}

	public int getProcessSize() {
		return processSize;
  
	}

	public void setProcessSize(int processSize) {
		this.processSize = processSize;
	}

}
