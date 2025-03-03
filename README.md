# 🏥 Sistema de Gestión de Medicamentos y Productos

## 📖 Descripción del Proyecto
Este proyecto es una plataforma de gestión de medicamentos y productos, desarrollada con un **backend en Spring Boot** y un **frontend en React (Next.js)**.

La aplicación permite:
- Buscar medicamentos por tipo o nombre.
- Crear y eliminar tipos de medicamentos.
- Crear medicamentos y asociarlos a un tipo.
- Verificar si un medicamento es prioritario.
- Ordenar y filtrar productos según diferentes criterios.

---

## 📌 Requerimientos Funcionales
### **🎯 Como usuario, quiero:**
- Crear un nuevo medicamento ingresando:
  - Código numérico.
  - Nombre comercial.
  - Nombre de la droga.
  - Tipo de medicamento (Ej: aerosol, crema, colirio, etc).
- Listar medicamentos por tipo o por prefijo de nombre comercial.
- Ordenar medicamentos por código.
- Verificar si un medicamento es prioritario.
- Gestionar (crear/eliminar) tipos de medicamentos.

---

## 🏗️ Arquitectura del Proyecto
### **Backend** (Spring Boot)
- **Microservicio de Medicamentos** (`medication`)
- **Microservicio de Productos** (`product`)
- Base de datos en **H2 en memoria** para desarrollo.

### **Frontend** (React con Next.js)
- UI con **TailwindCSS** y **Framer Motion**.
- Componentes modulares para la gestión de medicamentos.
- Conexión con el backend mediante **Axios**.

### **Base de Datos**
- **H2 en memoria** en ambiente de desarrollo.
- Puede usarse **PostgreSQL o MySQL** en producción.
- Se inicializa con datos de prueba mediante un job de inicio.

---

## 🚀 Instalación y Ejecución
### **1️⃣ Clonar el Repositorio**
```sh
git clone https://github.com/pablo092/mbsoftchallenge.git
cd mbsoftchallenge
```

### **2️⃣ Configurar Backend**
```sh
cd medication
mvn clean install
mvn spring-boot:run
```

```sh
cd product
mvn clean install
mvn spring-boot:run
```

### **3️⃣ Configurar Frontend**
```sh
cd medication-ui
npm install
npm run dev
```

Por defecto, la aplicación estará disponible en **http://localhost:3000**.

---

## 🌍 Despliegue
### **📌 Backend**
- Se recomienda empaquetar los servicios como **Docker Containers**.
- Puede desplegarse en **AWS, Heroku, DigitalOcean o cualquier VM con Java y Spring Boot**.

### **📌 Frontend**
- Se recomienda usar **Vercel o Netlify** para el despliegue.
- También puede alojarse en un servidor Nginx.

---

## 📡 API Endpoints
### **🩺 Microservicio de Medicamentos** (`/api/medications`)
| Método  | Endpoint               | Descripción                          |
|---------|------------------------|--------------------------------------|
| `POST`  | `/type`                 | Crear un nuevo tipo de medicamento. |
| `DELETE` | `/type/{id}`            | Eliminar un tipo de medicamento.    |
| `GET`   | `/byType`               | Listar medicamentos por tipo.       |
| `GET`   | `/byName`               | Listar por nombre comercial.        |
| `POST`  | `/`                     | Crear un nuevo medicamento.         |

### **📦 Microservicio de Productos** (`/api/products`)
| Método  | Endpoint        | Descripción                     |
|---------|----------------|---------------------------------|
| `POST`  | `/isPrioritary` | Verificar si un producto es prioritario. |
| `POST`  | `/verify`       | Validar código de un producto. |
| `POST`  | `/sortByCode`   | Ordenar productos por código.  |
| `POST`  | `/union`        | Obtener unión de dos listas.   |
| `POST`  | `/intersect`    | Obtener intersección de listas. |

---

## ✅ Pruebas
### **🧪 Backend**
Las pruebas están implementadas con **JUnit** y **Mockito**.
```sh
cd medication
mvn test
```
```sh
cd product
mvn test
```

### **🧪 Frontend**
Las pruebas están implementadas con **Jest y React Testing Library**.
```sh
cd medication-ui
npm test
```

---
