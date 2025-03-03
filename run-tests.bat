@echo off
setlocal

:: Directorios de los proyectos
set MEDICATION_SERVICE_DIR=medication
set PRODUCT_SERVICE_DIR=product
set MEDICATION-UI_DIR=medication-ui

:: Ejecutar tests en Medication Service
echo 🧪 Ejecutando tests en Medication Service...
cd %MEDICATION_SERVICE_DIR%
call mvnw.cmd test
cd ..

:: Ejecutar tests en Product Service
echo 🧪 Ejecutando tests en Product Service...
cd %PRODUCT_SERVICE_DIR%
call mvnw.cmd test
cd ..

:: Ejecutar tests en Frontend
echo 🧪 Ejecutando tests en Frontend...
cd %MEDICATION-UI_DIR%
call npm install
call npm test -- --watchAll=false
cd ..

echo ✅ Todos los tests han sido ejecutados.
pause
