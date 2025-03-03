import React from "react";

export default function ErrorMessage({ message }) {
  return message ? <p className="text-center mt-4 text-red-500">{message}</p> : null;
}
