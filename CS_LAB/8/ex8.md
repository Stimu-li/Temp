# Exercise 8: Virtual Machine Setup and C Programming

**Date:** _______________

**Aim:** To install VirtualBox/VMware Workstation with different flavours of Linux or Windows OS on top of Windows 7/8/10 and execute simple C programs.

## Algorithm

1. **VMware Installation:**
   - Download VMware Workstation from official website
   - Run installation wizard with proper configuration
   - Accept license agreement and configure installation paths
   - Set user experience settings and shortcuts
   - Complete installation and restart system

2. **Ubuntu Virtual Machine Setup:**
   - Create new virtual machine with custom settings
   - Configure ISO image path for Ubuntu installation
   - Set memory allocation (8GB) and storage (110GB)
   - Configure network settings (bridged networking)
   - Install Ubuntu operating system

3. **C Programming Environment:**
   - Update package repositories in Ubuntu
   - Install GCC compiler
   - Create, compile, and execute C programs

## Concept

- **Virtualization:** Technology that allows running multiple operating systems on a single physical machine
- **Hypervisor:** Software layer that manages virtual machines (VMware Workstation, VirtualBox)
- **Guest OS:** Operating system running inside virtual machine (Ubuntu)
- **Host OS:** Primary operating system on physical machine (Windows)
- **Cross-platform Development:** Developing software on different operating systems

## Installation Steps

### VMware Workstation Installation

1. Download VMware-workstation-full-16.1.2 from <https://www.vmware.com>
2. Run the downloaded .exe file to start installation wizard
3. Accept license agreement and click Next
4. Choose installation directory (default C: drive or custom location)
5. Enable options:
   - Enhanced Keyboard Driver
   - Add VMware Workstation console tools to system PATH
6. Configure User Experience Settings:
   - Check for product updates on startup
   - Join VMware Customer Experience Improvement Program
7. Create shortcuts:
   - Desktop shortcut
   - Start Menu Program Folder
8. Click Install to begin installation process
9. Restart system when prompted
10. Enter license key: `ZF3R0-FHED2-M80TY-8QYGC-NPKYF`
11. Complete setup and launch VMware Workstation

### Ubuntu Virtual Machine Creation

1. Visit <http://www.ubuntu.com/download/ubuntu/download>
2. Launch VMware Workstation
3. Click "Create a New Virtual Machine"
4. Select "Custom (advanced)" option
5. Choose "Installer disk image file (iso)" and browse to Ubuntu ISO
6. Set credentials:
   - Full name: ubuntu
   - Username: ubuntu
   - Password: cse123
7. Configure virtual machine settings:
   - Name: Ubuntu (or custom name)
   - Location: Default or custom path
   - Memory: 8GB (8192MB)
   - Network: Use bridged networking
   - SCSI Controller: LSI Logic
   - Virtual Disk Type: SCSI
   - Disk: Create new virtual disk (110GB, split into multiple files)
8. Power on virtual machine after creation
9. Complete Ubuntu installation process
10. Restart and login with credentials

### C Programming Setup

```bash
# Update package repositories
sudo apt update
sudo apt upgrade -y

# Install GCC compiler
sudo apt install gcc

# Create C program file
nano hello.c

# Sample C program content:
#include<stdio.h>
int main()
{
    printf("Hello World!\n");
    return 0;
}

# Compile the program
gcc hello.c

# Execute the program
./a.out
```

## Sample C Program

```c
#include<stdio.h>

int main()
{
    printf("Hello World from Virtual Machine!\n");
    printf("This program is running on Ubuntu in VMware\n");
    return 0;
}
```

## Sample Output

```text
# Compilation process
$ gcc hello.c
$ ./a.out
Hello World from Virtual Machine!
This program is running on Ubuntu in VMware

# System information
$ uname -a
Linux ubuntu 5.11.0-27-generic #29~20.04.1-Ubuntu SMP Wed Aug 11 15:58:17 UTC 2021 x86_64 x86_64 x86_64 GNU/Linux

$ gcc --version
gcc (Ubuntu 9.4.0-1ubuntu1~20.04.1) 9.4.0
```

## Key Configuration Details

- **Memory Allocation:** 8GB RAM for smooth Ubuntu operation
- **Storage:** 110GB virtual disk with SCSI interface
- **Network:** Bridged networking for external network access
- **Compiler:** GCC (GNU Compiler Collection) for C programming
- **File System:** Ext4 file system in Ubuntu virtual environment

## Advantages of Virtual Machines

1. **Isolation:** Separate environment for testing and development
2. **Multi-OS Support:** Run multiple operating systems simultaneously
3. **Snapshot Feature:** Save and restore system states
4. **Resource Management:** Allocate specific CPU, memory, and storage
5. **Security:** Contained environment reduces host system risks

## Troubleshooting Tips

- **Low Memory:** Allocate at least 4GB RAM for Ubuntu
- **Storage Issues:** Ensure host system has sufficient free space
- **Network Problems:** Check bridged adapter settings
- **Performance:** Enable virtualization in BIOS/UEFI settings
- **ISO Mount:** Verify Ubuntu ISO file integrity

## Result

Thus the installation of VMware with Ubuntu 20.04 OS on top of Windows 7/8/10 and execution of simple C programs was completed successfully.

---

**Exercise completed:** _______________