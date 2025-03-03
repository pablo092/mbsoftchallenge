@echo off
setlocal

:: Directorios de los proyectos
set MEDICATION_SERVICE_DIR=medication
set PRODUCT_SERVICE_DIR=product
set MEDICATION_UI_DIR=medication-ui

:: Iniciar Medication Service en nueva ventana
echo 🚀 Iniciando Medication Service...
cd %MEDICATION_SERVICE_DIR%
call mvnw.cmd clean package -DskipTests
start cmd /k "java -jar target/*.jar"
cd ..

:: Iniciar Product Service en nueva ventana
echo 🚀 Iniciando Product Service...
cd %PRODUCT_SERVICE_DIR%
call mvnw.cmd clean package -DskipTests
start cmd /k "java -jar target/*.jar"
cd ..

:: Iniciar Frontend en nueva ventana
echo 🚀 Iniciando Frontend...
cd %MEDICATION_UI_DIR%
call npm install
start cmd /k "npm run dev"
cd ..

echo ✅ Todos los servicios han sido iniciados correctamente.
pause
