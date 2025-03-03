# Usa Node.js como base
FROM node:18-alpine

WORKDIR /app

# Copia el código fuente
COPY . .

# Instala dependencias
RUN npm install

# Construye la aplicación
RUN npm run build

# Expone el puerto del frontend
EXPOSE 3000

# Ejecuta la aplicación en producción
CMD ["npm", "run", "start"]
