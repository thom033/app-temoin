@echo off
setlocal
@REM  temp folder
set "temp=C:\Users\Thomis\Documents\temp_MVC"

@REM lib folder
set "lib=%cd%\lib"
 
@REM web.xml file                          
set "xml=%cd%\web.xml" 

@REM dispatcher file
set "dispatcher=%cd%\WEB-INF\dispatcherServlet.xml"

@REM web folder   
set "web=%cd%\pages"  

@REM SRC
set "src=%cd%\src"

@REM assets folder   
set "assets=%cd%\assets"  

@REM webapps folder
set "webapps=D:\Program\Tomcat 10.1\webapps"

@REM Supprime le dossier temporaire 
rmdir /s /q "%temp%"
echo Dossier temporaire supprimé. 

@REM Recrée le dossier temporaire
mkdir "%temp%"

@REM Copie web vers lib
xcopy /s /e /y "%lib%" "%temp%\WEB-INF\lib\"

@REM Copie web/views vers WEB-INF/views/jsp/
xcopy /s /e /y "%web%" "%temp%"

@REM Copie xml vers web
copy /Y %xml% "%temp%\WEB-INF\"

@REM Copie dispatcher vers web
copy /Y %dispatcher% "%temp%\WEB-INF\"

@REM Copie les fichiers assets dans le dossier temporaire
xcopy /s /e /y "%assets%" "%temp%\assets\"

mkdir "%temp%\WEB-INF\classes\"

@REM Compiler les fichiers .java
for /R "%src%" %%f in (*.java) do (
    javac -cp "%lib%\*;%src%" -d "%temp%\WEB-INF\classes" "%%f"
)

@REM get my folder name
for /D %%i in ("%cd%") do set "myfolder=%%~nxi"

@REM Créer un fichier .war à partir du dossier temp
jar -cvf %myfolder%.war -C "%temp%" .

@REM Déplacer le fichier .war vers le dossier webapps de Tomcat
move %myfolder%.war "%webapps%"

endlocal
