import { render, screen } from "@testing-library/react";
import ErrorMessage from "../src/components/ErrorMessage";
import React from "react";

test("muestra un mensaje de error cuando hay un error", () => {
  render(<ErrorMessage message="Error al cargar datos" />);
  expect(screen.getByText("Error al cargar datos")).toBeInTheDocument();
});

test("no muestra nada cuando el mensaje está vacío", () => {
  const { container } = render(<ErrorMessage message="" />);
  expect(container.firstChild).toBeNull();
});
