# Exercise 9: Cloud Simulation and Custom Scheduling Algorithm

**Date:** _______________

**Aim:** To simulate a cloud scenario using CloudSim and run a scheduling algorithm that is not present in CloudSim.

## Algorithm

1. **CloudSim Setup:**
   - Download and extract CloudSim 3.0.3 framework
   - Download and configure Commons Math 3.6.1 library
   - Set up Eclipse IDE project with proper dependencies
   - Configure Java 1.8 environment

2. **Default Scheduling (First Come First Serve):**
   - Bind cloudlets to VMs in round-robin fashion
   - Simple index-based assignment without load consideration
   - Sequential allocation based on arrival order

3. **Custom Scheduling (Shortest Job First):**
   - Calculate current load on each VM
   - Find VM with minimum current load
   - Assign new cloudlet to least loaded VM
   - Update VM load after each assignment

4. **Simulation Execution:**
   - Create datacenter with configured resources
   - Deploy VMs with specified characteristics
   - Submit cloudlets with different lengths
   - Execute simulation and analyze results

## Concept

- **Cloud Simulation:** Modeling cloud computing environments to test resource allocation strategies
- **CloudSim Framework:** Java-based toolkit for modeling cloud computing infrastructure
- **Scheduling Algorithms:** Methods to assign computational tasks to available resources
- **Load Balancing:** Distributing workload evenly across multiple computing resources
- **Virtual Machines (VMs):** Virtualized computing resources with defined CPU, memory, and storage
- **Cloudlets:** Computational tasks submitted to cloud infrastructure for execution

## CloudSim Installation Steps

### Download Required Components

1. CloudSim 3.0.3 framework from official repository
2. Commons Math 3.6.1 library for mathematical operations

### Setup Dependencies

```bash
# Extract CloudSim-3.0.3.zip
# Extract commons-math-3.6.1-bin.zip
# Copy commons-math-3.6.1.jar to CloudSim-3.0.3/jars/
```

### Eclipse Project Configuration

1. Open Eclipse IDE
2. Create new Java Project
3. Set project name (e.g., "CloudIntro")
4. Uncheck "Use default location"
5. Browse and set path to CloudSim-3.0.3 directory
6. Select Java 1.8 JRE environment
7. Import all JAR files from CloudSim framework
8. Complete project setup

## Java Implementation

### First Come First Serve Scheduling

```java
/**
 * Simple Round-Robin scheduling algorithm
 * Assigns cloudlets to VMs in sequential order
 */
public void bindCloudletsToVmsSimple() {
    int cloudletNum = cloudletList.size();
    int vmNum = vmList.size();
    int idx = 0;
    
    // Round-robin assignment
    for (int i = 0; i < cloudletNum; i++) {
        cloudletList.get(i).setVmId(vmList.get(idx).getId());
        idx = (idx + 1) % vmNum;
    }
}
```

### Custom Shortest Job First Scheduling

```java
/**
 * Load-aware scheduling algorithm
 * Assigns cloudlets to VM with minimum current load
 */
public void bindCloudletToVmsScheduling() {
    int cloudletNum = cloudletList.size();
    int vmNum = vmList.size();
    double[] vmLoad = new double[vmNum];
    int idx = 0;
    
    // Assign first cloudlet to first VM
    cloudletList.get(0).setVmId(vmList.get(0).getId());
    vmLoad[0] += cloudletList.get(0).getCloudletLength() / vmList.get(0).getMips();
    
    // Assign remaining cloudlets to least loaded VM
    for (int j = 1; j < cloudletNum; j++) {
        // Find VM with minimum load
        for (int i = 0; i < vmNum; i++) {
            if (vmLoad[i] < vmLoad[idx]) {
                idx = i;
            }
        }
        
        // Assign cloudlet to least loaded VM
        cloudletList.get(j).setVmId(vmList.get(idx).getId());
        vmLoad[idx] += cloudletList.get(j).getCloudletLength() / vmList.get(idx).getMips();
    }
}
```

## Integration Steps

### Modify DatacenterBroker.java

1. Add both scheduling methods to DatacenterBroker class
2. Import necessary CloudSim packages
3. Ensure proper access to cloudletList and vmList

### Update Example Code

```java
// Replace default scheduling with custom algorithm
broker.bindCloudletToVmsScheduling();
```

### Execution Process

1. Compile the project in Eclipse
2. Run the extended example
3. Observe scheduling behavior in console output

## Sample Output

```text
========== OUTPUT ==========
Cloudlet ID    STATUS    Data center ID    VM ID    Time    Start Time    Finish Time
0              SUCCESS   2                 0        60      0.1           60.1
2              SUCCESS   2                 0        70      60.1          130.1
1              SUCCESS   2                 1        140     0.1           140.1

ExtendedExample finished!

========== SIMULATION STATISTICS ==========
Total Simulation Time: 140.1 seconds
Number of Cloudlets: 3
Number of VMs: 2
Average Execution Time: 90.03 seconds
Load Distribution:
- VM 0: 130.0 seconds (2 cloudlets)
- VM 1: 140.0 seconds (1 cloudlet)
```

## Algorithm Comparison

| Metric | FCFS (Round-Robin) | Custom (Load-Aware) |
|--------|-------------------|---------------------|
| **Complexity** | O(n) | O(n²) |
| **Load Balance** | Poor | Better |
| **Response Time** | Variable | Optimized |
| **Throughput** | Standard | Improved |

## Key CloudSim Components

- **Datacenter:** Physical infrastructure hosting VMs
- **DatacenterBroker:** Mediates between users and cloud services
- **VM (Virtual Machine):** Computational resource with defined capabilities
- **Cloudlet:** Computational task with specified length and requirements
- **Host:** Physical machine hosting multiple VMs

## Advanced Features

1. **Dynamic Load Balancing:** Real-time adjustment based on current VM utilization
2. **Priority-based Scheduling:** Consider cloudlet priorities in assignment decisions
3. **Resource Monitoring:** Track CPU, memory, and bandwidth utilization
4. **Cost Optimization:** Minimize execution cost while meeting performance requirements

## Detailed Procedure

### Step 1: Environment Setup

1. Download CloudSim 3.0.3 zip file and extract it
2. Download Commons Math 3.6.1 zip file and extract it
3. Copy commons-math-3.6.1.jar file to CloudSim-3.0.3/jars/ directory

### Step 2: Eclipse Configuration

1. Open Eclipse IDE
2. Click File → New → Java Project
3. Enter project name (e.g., "CloudIntro")
4. Uncheck "Use default location"
5. Browse and set path to CloudSim-3.0.3 directory
6. Select Java 1.8 JRE environment
7. Click Next - all JAR files will be displayed
8. Click Finish to complete setup

### Step 3: Implementation

1. Navigate to DatacenterBroker.java in CloudSim package
2. Add both scheduling methods to the class
3. Modify example code to use custom scheduling
4. Test with sample cloudlets and VMs

### Step 4: Execution and Analysis

1. Run the simulation
2. Analyze output for scheduling effectiveness
3. Compare performance metrics between algorithms
4. Document results and observations

## Troubleshooting Tips

- **JAR File Issues:** Ensure all dependencies are properly added to build path
- **Java Version:** CloudSim 3.0.3 requires Java 1.8 or compatible version
- **Memory Settings:** Increase Eclipse heap size for large simulations
- **Compilation Errors:** Check import statements and class dependencies

## Result

Thus the cloud scenario using CloudSim to run a custom scheduling algorithm that is not present in CloudSim was executed successfully.

---

**Exercise completed:** _______________