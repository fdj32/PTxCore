
CLASSPATH=$CLASSPATH:../bin

CLASSPATH=$CLASSPATH:../lib/commons-codec-1.4.jar
CLASSPATH=$CLASSPATH:../lib/commons-io-1.4.jar
CLASSPATH=$CLASSPATH:../lib/commons-lang-2.4.jar


/home/nfeng/jdk1.8.0_74/bin/java -Dgnu.io.rxtx.SerialPorts=/dev/ttyS0:/dev/ttyACM0 -classpath $CLASSPATH serial.Clock