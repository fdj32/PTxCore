# PTxCore

http://rxtx.qbang.org/wiki/index.php/Main_Page

http://www.4byte.cn/question/814741/how-to-make-scilab-open-a-serial-communication-with-dev-ttyacm0-usb-port-in-linux-ubuntu.html

sudo usermod -a -G dialout <USER_NAME>

When the pinpad is connected to a linux system, the new device descriptor "/dev/ttyACM0" is created.

http://rxtx.qbang.org/pub/rxtx/

add rxtx-2.1-7r2 source to src

http://stackoverflow.com/questions/7562565/how-to-get-javax-comm-api

http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-misc-419423.html

Linux 3.0 doesn't work.

rxtx-2.2 is ok for linux 64

# MACOSX

copy /PTxCore/doc/rxtx-2.2pre2-bins/mac-10.5/librxtxSerial.jnilib to /Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib

in MacOSX, the pinpad serial port name is "/dev/tty.usbmodem1411"

You can run command "ls /dev", before and after the pinpad was connected with the host machine to see the difference.

# Reference

http://www.cnblogs.com/dodoJavaLearner/p/5428418.html
http://blog.csdn.net/kong_gu_you_lan/article/details/52302075
http://www.jb51.net/article/70621.htm
http://blog.csdn.net/linghao00/article/details/6852739


# Error In MacOSX

.
[06][02]58.00[03] [06][02]58.00[03] 
..............................................................................
read end
.#
.# A fatal error has been detected by the Java Runtime Environment:
.#
.#  SIGSEGV (0xb) at pc=0x000000011d96112f, pid=1336, tid=0x0000000000001c03
.#
.# JRE version: Java(TM) SE Runtime Environment (8.0_111-b14) (build 1.8.0_111-b14)
.# Java VM: Java HotSpot(TM) 64-Bit Server VM (25.111-b14 mixed mode bsd-amd64 compressed oops)
.# Problematic frame:
.# C  [librxtxSerial.jnilib+0x312f]  Java_gnu_io_RXTXPort_interruptEventLoop+0x6b
.#
.# Failed to write core dump. Core dumps have been disabled. To enable core dumping, try "ulimit -c unlimited" before starting Java again
.#
.# An error report file with more information is saved as:
.# /Users/nickfeng/Hub/fdj32/PTxCore/hs_err_pid1336.log
.#
.# If you would like to submit a bug report, please visit:
.#   http://bugreport.java.com/bugreport/crash.jsp
.# The crash happened outside the Java Virtual Machine in native code.
.# See problematic frame for where to report the bug.
.#
Problem closing serial port using rxtx api

https://github.com/digidotcom/XBeeJavaLibrary/issues/105
https://stackoverflow.com/questions/5979305/java-serialport-close-blocks
https://marc.info/?l=rxtx&m=123544269723586&w=2
https://community.oracle.com/thread/1294323


# jSSC
https://code.google.com/archive/p/java-simple-serial-connector/

# nrjavaserial
http://nrjs.org/
https://github.com/NeuronRobotics/nrjavaserial

# serial IO
https://serialio.com/
https://sourceforge.net/projects/serial-io/











