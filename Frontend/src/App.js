 import React, { useState } from "react";
import Register from "./Register";
import Login from "./Login";
import Payment from "./Payment";
import Home from "./Home";
import "./App.css";

function App() {
  const [step, setStep] = useState(1); // 1:Register, 2:Payment, 3:Login, 4:Home

  const handleRegisterNext = () => setStep(2);
  const handlePaymentNext = () => setStep(3);
  const handleLoginSuccess = () => setStep(4);

  const goToPayment = () => setStep(2);

  const renderStep = () => {
    if (step === 1) return <Register onNext={handleRegisterNext} />;
    if (step === 2) return <Payment onNext={handlePaymentNext} />;
    if (step === 3)
      return <Login onSuccess={handleLoginSuccess} goToPayment={goToPayment} />;
    if (step === 4) return <Home />;
  }; 

  return (
    <>
      <nav className="navbar navbar-expand-lg bg-light">
        <div className="container-fluid">
          <a className="navbar-brand fw-bold" href="#">CompanyName</a>
          <div>
            <button className="btn btn-outline-success me-2" onClick={() => setStep(3)}>Login</button>
            <button className="btn btn-outline-success" onClick={() => setStep(1)}>Register</button>
          </div>
        </div>
      </nav>

      {/* Only show progress bar if not on Home */}
      {step !== 4 && (
        <>
          <div className="container mt-4">
            <div className="progressbar-wrapper">
              <div className="progressbar">
                <div className={`step ${step === 1 ? "active" : ""}`}>1</div>
                <div className={`step ${step === 2 ? "active" : ""}`}>2</div>
                <div className={`step ${step === 3 ? "active" : ""}`}>3</div>
              </div>
              <div className="label-container">
                <div className="label">Registration</div>
                <div className="label">Payment</div>
                <div className="label">Login</div>
              </div>
            </div>
          </div>
        </>
      )}

      <div className="container mt-4">
        {renderStep()}
      </div>

      <footer className="bg-light text-center text-lg-start mt-auto">
        <div className="text-center p-3 bg-light text-dark">
          © 2025 Company Name. All rights reserved.
        </div>
      </footer>
    </>
  );
}

export default App;
