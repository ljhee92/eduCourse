call env.bat

:loading
echo loading...
timeout /t 2 /nobreak > NUL

echo loading complete!
echo start sequence driving
call java -jar eduCourse_.jar

