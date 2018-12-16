Welcome to the EntertainmentBox
==================================================

Getting Started
---------------

1. Install maven.  See https://maven.apache.org/install.html for details.

2. Install tomcat.  See https://tomcat.apache.org/tomcat-8.0-doc/setup.html for
   details.

3. Build the application.

        $ mvn -f pom.xml compile
        $ mvn -f pom.xml package

4. Copy the built application to the Tomcat webapp directory.  The actual
   location of that directory will vary depending on your platform and
   installation.

        $ cp target/ROOT.war <tomcat webapp directory>

4. Restart your tomcat server

5. Open http://127.0.0.1:8080/ in a web browser to view your application.

