import React from 'react';
import { Routes, Route } from 'react-router-dom';  // NO Router here
import PricingPlans from './Payment/PricingPlans';
import PaymentPage from './Payment/Payment';

function App() {
  return (
    <Routes>
      <Route path="/" element={<PricingPlans />} />
      <Route path="/payment" element={<PaymentPage />} />
    </Routes>
  );
}

export default App;
