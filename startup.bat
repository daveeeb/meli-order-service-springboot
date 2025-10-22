@echo off
REM Script to start the order management service on Windows
REM Assuming the project is compiled using the Maven Wrapper (mvnw.cmd)

echo -> Building the Spring Boot application...
call mvnw clean package -DskipTests

REM Check if the build was successful (ErrorLevel 0 means success)
if %ERRORLEVEL% NEQ 0 (
    echo Error: The application build failed. Exiting.
    goto :eof
)

REM Find the packaged JAR file
SET JAR_FILE=
FOR %%f IN (target\*.jar) DO SET JAR_FILE=%%f

if "%JAR_FILE%"=="" (
    echo Error: JAR file not found in the target directory. Exiting.
    goto :eof
)

REM Execute the Spring Boot application
echo -> Starting the Order Service...
echo Application will run on http://localhost:8080
java -jar "%JAR_FILE%"

:eof