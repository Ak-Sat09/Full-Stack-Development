 import React, { useState } from "react";
import Register from "./Register";
import Login from "./Login";
import "./App.css";

function App() {
  const [step, setStep] = useState(1); // 1: Register, 2: Payment (Future), 3: Login

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

      <div className="container mt-5">
        {/* Progress Bar */}
        <div className="progressbar-wrapper">
          <div className="progressbar">
            <div className={`step ${step === 1 ? "active" : ""}`}>1</div>
            <div className={`step ${step === 2 ? "active" : ""}`}>2</div>
            <div className={`step ${step === 3 ? "active" : ""}`}>3</div>
          </div>
        </div>

        <div className="label-container">
          <div className="label">Registration</div>
          <div className="label">Payment</div>
          <div className="label">Submission</div>
        </div>

        {step === 1 && <Register onNext={() => setStep(3)} />}
        {step === 3 && <Login />}
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
