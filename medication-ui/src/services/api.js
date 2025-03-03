import axios from "axios";

// Configuración para la API de medicamentos
export const medicationApi = axios.create({
  baseURL: process.env.NEXT_PUBLIC_MEDICATION_API_URL || "http://localhost:8082/api",
  headers: {
    "Content-Type": "application/json",
  },
});

// Configuración para la API de productos
export const productApi = axios.create({
  baseURL: process.env.NEXT_PUBLIC_PRODUCT_API_URL || "http://localhost:8081/api",
  headers: {
    "Content-Type": "application/json",
  },
});
