import { render, screen, fireEvent } from "@testing-library/react";
import Modal from "../src/components/ui/Modal";
import React from "react";

test("cierra el modal al hacer clic en el botón de cerrar", () => {
  const mockOnClose = jest.fn();

  render(
    <Modal title="Test Modal" isOpen={true} onClose={mockOnClose}>
      <p>Contenido del modal</p>
    </Modal>
  );

  fireEvent.click(screen.getByText("Cerrar"));
  expect(mockOnClose).toHaveBeenCalledTimes(1);
});

test("muestra el contenido cuando el modal está abierto", () => {
  render(
    <Modal title="Test Modal" isOpen={true} onClose={() => {}}>
      <p>Contenido del modal</p>
    </Modal>
  );

  expect(screen.getByText("Contenido del modal")).toBeInTheDocument();
});

test("no renderiza nada cuando isOpen es false", () => {
  const { container } = render(
    <Modal title="Test Modal" isOpen={false} onClose={() => {}}>
      <p>Contenido del modal</p>
    </Modal>
  );

  expect(container.firstChild).toBeNull();
});
