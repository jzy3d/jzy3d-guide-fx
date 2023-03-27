# jzy3d-guide-fx

This project provides sample demonstrating how to use Jzy3D with JavaFX for various Java version.

## jzy3d-guide-javafx-jdk8

Demonstrate how to use JavaFX for legacy Java8 VM.

See `DemoJavaFX_Offscreen_OpenJDK8_Surface`

## jzy3d-guide-javafx-jdk17

Demonstrate how to use JavaFX for Java17 VM.

See `DemoJavaFX_Offscreen_Gluon17`

It comes with a patch of JOGL's JAWTUtil class that is not required to run on JDK17 but that does not prevent to run on JDK17 either. It is mainly introduced here for later JDK upgrades since the patch become necessary as of JDK19.

## jzy3d-guide-javafx-jdk19

Demonstrate how to use JavaFX for Java19 VM.

See `DemoJavaFX_Offscreen_Gluon19`

It comes with a patch of JOGL's JAWTUtil class that is  required to run on JDK19 (otherwise the application hangs at startup).
