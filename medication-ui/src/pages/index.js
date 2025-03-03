import React, { useState } from "react";
import {
  getMedicationsByType,
  getMedicationsByNamePrefix,
} from "../services/medicationService";
import {
  sortProducts,
  isPrioritary,
  verifyProduct,
} from "../services/productService";
import { motion } from "framer-motion";
import { Loader } from "../components/ui/Loader";
import MedicationActions from "../components/medications/MedicationActions";
import MedicationForm from "../components/medications/MedicationForm";
import MedicationTypeForm from "../components/medications/MedicationTypeForm";
import MedicationList from "../components/medications/MedicationList";
import MedicationSortButton from "../components/medications/MedicationSortButton";
import MedicationSearch from "../components/medications/MedicationSearch";
import ErrorMessage from "../components/ErrorMessage";
import NoResultsMessage from "../components/NoResultsMessage";

export default function Home() {
  const [medications, setMedications] = useState([]);
  const [type, setType] = useState("");
  const [searchType, setSearchType] = useState("byType");
  const [medError, setMedError] = useState("");
  const [loading, setLoading] = useState(false);
  const [sorted, setSorted] = useState(false);
  const [noResults, setNoResults] = useState(false);
  const [showTypeModal, setShowTypeModal] = useState(false);
  const [showMedicationModal, setShowMedicationModal] = useState(false);

  const fetchMedications = async () => {
    if (type.trim() === "") return;
    setLoading(true);
    setMedError("");
    setNoResults(false);
    try {
      const data =
        searchType === "byType"
          ? await getMedicationsByType(type)
          : await getMedicationsByNamePrefix(type);
      setMedications(data);
      setNoResults(data.length === 0);
      setSorted(false);
    } catch (error) {
      setMedError("Error al obtener medicamentos.");
    } finally {
      setLoading(false);
    }
  };

  const handleSearchTypeChange = (type) => {
    setSearchType(type);
    setType("");
    setMedications([]);
    setMedError("");
    setNoResults(false);
    setSorted(false);
  };

  const checkPriority = async (code) => {
    try {
      const isPrioritaryProduct = await isPrioritary(code);
      alert(
        isPrioritaryProduct
          ? "Este producto es prioritario."
          : "Este producto no es prioritario."
      );
    } catch (error) {
      alert("Error al verificar la prioridad.");
    }
  };

  const checkCode = async (code) => {
    try {
      const isValid = await verifyProduct(code);
      alert(isValid ? "Código válido." : "Código inválido.");
    } catch (error) {
      alert("Error al verificar el código.");
    }
  };

  const handleSortProducts = async () => {
    try {
      const sortedList = await sortProducts(
        medications.map((m) => ({ code: m.code }))
      );
      setMedications(
        sortedList.map((sorted) =>
          medications.find((m) => m.code === sorted.code)
        )
      );
      setSorted(true);
    } catch (error) {
      alert("Error al ordenar los productos.");
    }
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 p-6">
      <motion.div
        className="w-full max-w-3xl bg-white shadow-lg rounded-lg p-6"
        initial={{ opacity: 0, y: -20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
      >
        <h1 className="text-2xl font-bold text-center text-blue-600 flex items-center space-x-2">
          <span>🔎</span>
          <span>Búsqueda de Medicamentos</span>
        </h1>

        <MedicationSearch
          type={type}
          setType={setType}
          searchType={searchType}
          handleSearchTypeChange={handleSearchTypeChange}
          fetchMedications={fetchMedications}
        />

        <MedicationActions
          setShowTypeModal={setShowTypeModal}
          setShowMedicationModal={setShowMedicationModal}
        />

        {loading && <Loader />}
        <ErrorMessage message={medError} />
        {noResults && <NoResultsMessage />}

        {medications.length > 0 && (
          <>
            <h2 className="text-lg font-semibold mt-6">Medicamentos</h2>
            {!sorted && (
              <MedicationSortButton handleSortProducts={handleSortProducts} />
            )}
            <MedicationList
              medications={medications}
              checkPriority={checkPriority}
              checkCode={checkCode}
            />
          </>
        )}

        {/* Modales */}
        <MedicationForm
          isOpen={showMedicationModal}
          onClose={() => setShowMedicationModal(false)}
        />
        <MedicationTypeForm
          isOpen={showTypeModal}
          onClose={() => setShowTypeModal(false)}
        />
      </motion.div>
    </div>
  );
}
