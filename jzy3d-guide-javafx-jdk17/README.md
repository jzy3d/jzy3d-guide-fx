To be built with JDK17, involving JavaFX 17 to be provided (Gluon distrib).
Was also verified with JDK19

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-19.0.2.jdk/Contents/Home/

Note one tricky thing : we provided a patched version of a JOGL class to prevent JOGL to hang at JVM startup. It is optional in JDK17 but mandatory with JDK19.
