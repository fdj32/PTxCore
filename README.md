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

