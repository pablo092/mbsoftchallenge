import { productApi } from "./api";

// Verifica si el producto es prioritario
export const isPrioritary = async (code) => {
  try {
    const response = await productApi.post("/products/isPrioritary", { code });
    return response.data;
  } catch (error) {
    throw new Error("Error al verificar la prioridad del producto.");
  }
};

// Verifica si el código del producto es válido
export const verifyProduct = async (code) => {
  try {
    const response = await productApi.post("/products/verify", { code });
    return response.data;
  } catch (error) {
    throw new Error("Error al verificar el código del producto.");
  }
};

// Ordena una lista de productos
export const sortProducts = async (products) => {
  try {
    const response = await productApi.post("/products/sortByCode", products);
    return response.data;
  } catch (error) {
    throw new Error("Error al ordenar los productos.");
  }
};
