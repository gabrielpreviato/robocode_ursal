# URSAL Robocode

## Build .class
javac -classpath "<dir_to_robocode>/libs/robocode.jar" URSAL/URSAL.java

## Package .jar
jar cvfm URSAL.jar MANIFEST.MF URSAL/URSAL.java URSAL/URSAL.class URSAL/URSAL.properties
