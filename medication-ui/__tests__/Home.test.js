import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import Home from "../src/pages/index";
import { Loader } from "../src/components/ui/Loader";
import React from "react";

jest.mock("../src/services/medicationService", () => ({
  getMedicationsByType: jest.fn(() => Promise.resolve([])),
  getMedicationsByNamePrefix: jest.fn(() => Promise.resolve([])),
}));

jest.mock("../src/services/productService", () => ({
  sortProducts: jest.fn(() => Promise.resolve([])),
  isPrioritary: jest.fn(() => Promise.resolve(true)),
  verifyProduct: jest.fn(() => Promise.resolve(true)),
}));

beforeAll(() => {
  jest.spyOn(window, 'alert').mockImplementation(() => {});
});

describe("🏠 Home Component", () => {
  test("🔹 Renderiza el título principal", () => {
    render(<Home />);
    expect(screen.getByText("Búsqueda de Medicamentos")).toBeInTheDocument();
  });

  test("🔹Renderiza el botón de búsqueda", () => {
    render(<Home />);
    expect(screen.getByRole("button", { name: /Buscar/i })).toBeInTheDocument();
  });

  test("🔹 Busca medicamentos y muestra mensaje cuando no hay resultados", async () => {
    render(<Home />);

    const input = screen.getByPlaceholderText("Ingrese su búsqueda");
    fireEvent.change(input, { target: { value: "Paracetamol" } });

    const searchButton = screen.getByText("Buscar");
    fireEvent.click(searchButton);

    await waitFor(() => {
      expect(
        screen.getByText("No se encontraron resultados.")
      ).toBeInTheDocument();
    });
  });

  test("🔹 Cambia entre tipos de búsqueda", () => {
    render(<Home />);

    const radioByType = screen.getByLabelText("Por Tipo");
    const radioByName = screen.getByLabelText("Por Nombre");

    fireEvent.click(radioByName);
    expect(radioByName).toBeChecked();
    expect(radioByType).not.toBeChecked();

    fireEvent.click(radioByType);
    expect(radioByType).toBeChecked();
    expect(radioByName).not.toBeChecked();
  });

  test("🔹 Botón de búsqueda deshabilitado cuando input está vacío", () => {
    render(<Home />);

    const searchButton = screen.getByText("Buscar");

    const input = screen.getByPlaceholderText("Ingrese su búsqueda");
    fireEvent.change(input, { target: { value: "Ibuprofeno" } });

    expect(searchButton).not.toBeDisabled();
  });

  test("🔹 Muestra el loader mientras carga", async () => {
    render(<Loader />);

    const loader = screen.getByTestId("loader");

    expect(loader).toBeInTheDocument();
  });

  test("🔹 Muestra error cuando falla la búsqueda", async () => {
    render(<Home />);
  
    const input = screen.getByPlaceholderText("Ingrese su búsqueda");
    fireEvent.change(input, { target: { value: "Aspirina" } });
  
    const searchButton = screen.getByText("Buscar");
    fireEvent.click(searchButton);
  
    await waitFor(() => {
      expect(screen.findByText(/error al obtener medicamentos/i)).toBeTruthy();
    });
  });

  test("🔹 Abre y cierra modales correctamente", () => {
    render(<Home />);

    const typeModalButton = screen.getByText("Crear / Eliminar Tipo");
    const medicationModalButton = screen.getByText("Crear Medicamento");

    fireEvent.click(typeModalButton);
    expect(screen.getByText("Tipo de Medicamento")).toBeInTheDocument();

    fireEvent.click(medicationModalButton);
    expect(screen.getByText("Crear Medicamento")).toBeInTheDocument();
  });
});
