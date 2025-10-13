# Exercise 7: Intrusion Detection System (IDS) using Snort

**Date:** _______________

**Aim:** To demonstrate Intrusion Detection System (IDS) using SNORT in different operational modes.

## Algorithm

1. **Installation and Setup:**
   - Download SNORT from snort.org
   - Install SNORT with required components
   - Configure system environment variables
   - Set up logging directories

2. **Sniffer Mode Configuration:**
   - Use basic packet sniffing commands
   - Monitor network traffic in real-time
   - Display packet headers and data

3. **Packet Logger Mode Setup:**
   - Configure logging directory
   - Set up packet capture and storage
   - Apply network filters for specific IP ranges

4. **NIDS Mode Implementation:**
   - Configure snort.conf rule file
   - Apply intrusion detection rules
   - Monitor and log suspicious activities

5. **Testing and Verification:**
   - Generate network traffic
   - Verify packet capture and logging
   - Analyze detection results

## Concept

**SNORT Operational Modes:**

- **Sniffer Mode:** Real-time packet monitoring and display
- **Packet Logger Mode:** Captures and stores network packets to disk
- **Network Intrusion Detection System (NIDS) Mode:** Applies rules to detect intrusions

**Key Components:**

- **Packet Decoder:** Analyzes different protocol layers
- **Preprocessors:** Normalize and prepare packets for detection engine
- **Detection Engine:** Applies rules to identify suspicious activities
- **Logging and Alerting:** Records and reports security events
- **Rule Database:** Contains signatures for known attacks and anomalies

## Implementation

### Step 1: SNORT Installation

**Download and Install:**

1. Visit snort.org and download latest SNORT version
2. Run installer as Administrator
3. Select installation components:
   - SNORT executable
   - Configuration files
   - Rule sets
   - Documentation

**Installation Configuration:**

```cmd
# During installation:
- Select all components ✓
- Choose installation directory: C:\snort\
- Configure WinPcap (if required)
- Complete installation process
```

### Step 2: Environment Setup

**Configure System PATH:**

1. Open System Properties → Advanced → Environment Variables
2. Create new system variable:
   - Variable name: `PATH`
   - Variable value: `C:\snort\bin`
3. Click OK to save changes

**Verify Installation:**

```cmd
# Open Command Prompt as Administrator
C:\> snort --version

   ,,_     -*> Snort! <*-
  o"  )~   Version 2.9.20 GRE (Build 82)
   ''''    By Martin Roesch & The Snort Team

# Verify successful installation
```

### Step 3: Directory Structure Setup

**Create Required Directories:**

```cmd
# Create logging directory
C:\> mkdir C:\log

# Create configuration directory
C:\> mkdir C:\snort\etc

# Create rules directory  
C:\> mkdir C:\snort\rules

# Verify directory structure
C:\> dir C:\snort
```

### Step 4: Operational Mode Implementation

#### Mode 1: Sniffer Mode

**Basic Packet Sniffing:**

```cmd
# Display TCP/IP packet headers
C:\> snort -v

# Output:
10/14/25-15:30:25.123456  192.168.1.100:80 -> 192.168.1.50:1234
TCP TTL:64 TOS:0x0 ID:54321 IpLen:20 DgmLen:52
***A**** Seq: 0x12345678  Ack: 0x87654321  Win: 0x1000  TcpLen: 32

# Show headers with application data
C:\> snort -vd

# Output includes:
- Ethernet headers
- IP headers  
- TCP/UDP headers
- Application layer data (HTTP, FTP, etc.)
```

**Enhanced Sniffer Output:**

```cmd
# Verbose mode with data link layer
C:\> snort -vde

# Sample Output:
=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
10/14/25-15:30:25.456789  192.168.1.100 -> 192.168.1.50
Ethernet Header:
  Src MAC: 00:1B:44:11:3A:B7
  Dst MAC: 00:50:56:C0:00:01
  Type: 0x800 (IP)

IP Header:
  Version: 4  Header Length: 20 bytes
  Type of Service: 0x00
  Total Length: 84 bytes
  Identification: 0xD431
  Flags: 0x40 (Don't Fragment)
  Fragment Offset: 0x00
  Time to Live: 64
  Protocol: 6 (TCP)
  Header Checksum: 0x1234
  Source IP: 192.168.1.100
  Destination IP: 192.168.1.50

TCP Header:
  Source Port: 80 (HTTP)
  Destination Port: 1234
  Sequence Number: 0x12345678
  Acknowledgment: 0x87654321
  Data Offset: 8 (32 bytes)
  Flags: 0x18 (PSH ACK)
  Window Size: 4096
  Checksum: 0x5678
  Urgent Pointer: 0x0000

Application Data:
GET / HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0
```

#### Mode 2: Packet Logger Mode

**Basic Packet Logging:**

```cmd
# Log all packets to directory
C:\> snort -dev -l C:\log

# This command:
- Captures all network packets
- Logs to C:\log directory
- Includes data link, network, and application layers
- Creates separate files for different connections
```

**Filtered Packet Logging:**

```cmd
# Log packets for specific network
C:\> snort -dev -l C:\log -h 192.168.1.0/24

# Command breakdown:
- -d: Dump application layer data
- -e: Show data link layer headers  
- -v: Verbose packet information
- -l: Log to specified directory
- -h: Home network specification
```

**Binary Logging Mode:**

```cmd
# Log everything to single binary file
C:\> snort -l C:\log -b

# Creates unified binary log file:
# C:\log\snort.log.timestamp
# Efficient storage format
# Can be analyzed with snort or other tools
```

**Log File Analysis:**

```cmd
# Read binary log file
C:\> snort -r C:\log\snort.log.1634218825

# Filter and analyze logs
C:\> snort -r C:\log\snort.log.1634218825 -c snort.conf
```

#### Mode 3: Network Intrusion Detection System (NIDS)

**Basic NIDS Configuration:**

```cmd
# Run SNORT in NIDS mode
C:\> snort -d -h 192.168.1.0/24 -l C:\log -c C:\snort\etc\snort.conf

# Command explanation:
- -d: Show application data
- -h: Define home network
- -l: Specify log directory
- -c: Use configuration file with rules
```

**SNORT Configuration File (snort.conf):**

```bash
# snort.conf - Basic configuration

# Network Variables
var HOME_NET 192.168.1.0/24
var EXTERNAL_NET !$HOME_NET
var HTTP_SERVERS $HOME_NET
var SMTP_SERVERS $HOME_NET
var DNS_SERVERS $HOME_NET

# Port Variables
var HTTP_PORTS 80
var SHELLCODE_PORTS !80
var ORACLE_PORTS 1521

# Rule Paths
var RULE_PATH C:\snort\rules
var SO_RULE_PATH C:\snort\so_rules
var PREPROC_RULE_PATH C:\snort\preproc_rules

# Output Configuration
output alert_fast: C:\log\alert.txt
output log_tcpdump: C:\log\snort.log

# Include Rules
include $RULE_PATH\local.rules
include $RULE_PATH\icmp.rules
include $RULE_PATH\http.rules
```

**Sample Detection Rules:**

```bash
# local.rules - Custom detection rules

# Detect ICMP ping sweep
alert icmp $EXTERNAL_NET any -> $HOME_NET any (msg:"ICMP Ping Sweep"; sid:1000001; rev:1;)

# Detect suspicious HTTP traffic
alert tcp $EXTERNAL_NET any -> $HTTP_SERVERS $HTTP_PORTS (msg:"Suspicious HTTP Request"; content:"cmd.exe"; sid:1000002; rev:1;)

# Detect port scanning
alert tcp $EXTERNAL_NET any -> $HOME_NET any (msg:"Port Scan Detected"; flags:S; detection_filter:track by_src, count 10, seconds 60; sid:1000003; rev:1;)

# Detect SQL injection attempt
alert tcp $EXTERNAL_NET any -> $HTTP_SERVERS $HTTP_PORTS (msg:"SQL Injection Attempt"; content:"union select"; nocase; sid:1000004; rev:1;)

# Detect suspicious outbound traffic
alert tcp $HOME_NET any -> $EXTERNAL_NET any (msg:"Suspicious Outbound Connection"; content:"password"; sid:1000005; rev:1;)
```

### Step 5: Advanced NIDS Configuration

**Complete NIDS Setup:**

```cmd
# Full NIDS mode with all features
C:\> snort -A console -q -u snort -g snort -c C:\snort\etc\snort.conf -i 1

# Parameters:
- -A console: Alert to console
- -q: Quiet mode (reduce output)
- -u: Run as specific user
- -g: Run as specific group
- -i: Interface number (1 = first network interface)
```

**Real-time Monitoring:**

```cmd
# Monitor with real-time alerts
C:\> snort -A full -c C:\snort\etc\snort.conf -l C:\log

# Monitor specific interface
C:\> snort -A full -c C:\snort\etc\snort.conf -i "Local Area Connection"
```

### Step 6: Testing and Verification

**Generate Test Traffic:**

```cmd
# From another machine, generate traffic:
ping 192.168.1.100
nmap -sS 192.168.1.100
curl http://192.168.1.100/test?id=1'union select 1,2,3--
```

**Monitor Alerts:**

```cmd
# View real-time alerts
C:\> type C:\log\alert.txt

# Sample alert output:
[**] [1:1000001:1] ICMP Ping Sweep [**]
[Priority: 3] 
10/14/25-15:45:30.123456 192.168.1.200 -> 192.168.1.100
ICMP TTL:64 TOS:0x0 ID:12345 IpLen:28 DgmLen:56

[**] [1:1000003:1] Port Scan Detected [**]
[Priority: 2]
10/14/25-15:46:15.789012 192.168.1.200:45678 -> 192.168.1.100:80
TCP TTL:64 TOS:0x0 ID:23456 IpLen:20 DgmLen:40
***A**** Seq: 0x0  Ack: 0x0  Win: 0x2000  TcpLen: 20
```

**Log Analysis:**

```cmd
# Analyze captured packets
C:\> snort -r C:\log\snort.log

# Generate statistics
C:\> snort -r C:\log\snort.log -A console | findstr "Priority"

# Count alerts by type
C:\> type C:\log\alert.txt | findstr /C:"[**]" | find /C /V ""
```

## Key Features and Capabilities

### Detection Methods

1. **Signature-based Detection:**
   - Pattern matching against known attack signatures
   - Fast and accurate for known threats
   - Requires regular rule updates

2. **Anomaly-based Detection:**
   - Statistical analysis of network behavior
   - Detects unknown attacks and variations
   - Higher false positive rate

3. **Protocol Analysis:**
   - Deep packet inspection
   - Protocol compliance checking
   - Application layer analysis

### Performance Tuning

```bash
# Optimize performance in snort.conf
config detection: search-method ac-split
config event_queue: max_queue 8 log 3 order_events content_length
config flowbits_size: 64
config pcre_match_limit: 3500
config pcre_match_limit_recursion: 1500
```

### Advanced Rule Writing

```bash
# Complex rule example
alert tcp $EXTERNAL_NET any -> $HOME_NET $HTTP_PORTS (
    msg:"Advanced HTTP Attack"; 
    flow:to_server,established; 
    content:"POST"; http_method; 
    content:"admin"; http_uri; 
    pcre:"/password=.*['\";]/i"; 
    sid:1000010; 
    rev:1;
)
```

## Troubleshooting

**Common Issues:**

1. **WinPcap Driver Issues:**
   ```cmd
   # Reinstall WinPcap
   # Download from winpcap.org
   # Install as Administrator
   ```

2. **Permission Errors:**
   ```cmd
   # Run Command Prompt as Administrator
   # Check firewall settings
   # Verify user permissions
   ```

3. **Configuration Errors:**
   ```cmd
   # Test configuration
   C:\> snort -T -c C:\snort\etc\snort.conf
   
   # Check for syntax errors
   # Verify file paths
   # Validate rule syntax
   ```

## Best Practices

1. **Rule Management:**
   - Regular rule updates
   - Custom rule testing
   - Performance impact assessment

2. **Log Management:**
   - Regular log rotation
   - Automated analysis
   - Storage capacity planning

3. **Performance Optimization:**
   - Interface tuning
   - CPU affinity settings
   - Memory allocation optimization

4. **Security Considerations:**
   - Secure rule distribution
   - Access control implementation
   - Encrypted communications

## Sample Commands Summary

### Sniffer Mode Commands

```cmd
# Basic header display
snort -v

# Headers with application data
snort -vd

# Full packet analysis
snort -vde
```

### Packet Logger Mode Commands

```cmd
# Basic logging
snort -dev -l C:\log

# Filtered network logging
snort -dev -l C:\log -h 192.168.1.0/24

# Binary logging
snort -l C:\log -b
```

### NIDS Mode Commands

```cmd
# Basic NIDS
snort -d -h 192.168.1.0/24 -l C:\log -c snort.conf

# Advanced NIDS with console alerts
snort -A console -q -c C:\snort\etc\snort.conf -i 1

# Full monitoring mode
snort -A full -c C:\snort\etc\snort.conf -l C:\log
```

## Result

Thus the Intrusion Detection System (IDS) using SNORT was successfully demonstrated in all three operational modes:

- **Sniffer mode** for real-time packet monitoring and analysis
- **Packet Logger mode** for comprehensive traffic capture and storage  
- **NIDS mode** for automated intrusion detection and security alerting

The system effectively monitors network traffic, detects suspicious activities based on configurable rules, and provides comprehensive logging capabilities for security incident analysis and forensic investigation.

---

**Exercise completed:** _______________