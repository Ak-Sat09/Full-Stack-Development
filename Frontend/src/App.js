 import React, { useState } from "react";
import Register from "./Register";
import Login from "./Login";
import Payment from "./Payment";
import "./App.css";

function App() {
  const [step, setStep] = useState(1); // 1: Register, 2: Payment, 3: Login
  const [registered, setRegistered] = useState(false);
  const [paid, setPaid] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleRegisterNext = () => {
    setRegistered(true);
    setStep(2); // Go to payment
  };

  const handlePaymentNext = () => {
    setPaid(true);
    setStep(3); // Go to login
  };

  const handleLoginSuccess = () => {
    setIsLoggedIn(true); //  Mark user as logged in
  };

  const handleLoginClick = () => {
    if (!isLoggedIn) {
      if (!registered) return alert(" You must complete Registration first.");
      if (!paid) return alert(" You must complete Payment first.");
    }
    setStep(3); // Go to login
  };

  const handleRegisterClick = () => {
    if (!isLoggedIn && registered) return alert("You're already registered. Go to Payment or Login.");
    setStep(1);
  };

  const handlePaymentClick = () => {
    if (!isLoggedIn) {
      if (!registered) return alert("You must register first.");
      if (paid) return alert(" Already Paid. Go to Login.");
    }
    setStep(2);
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg bg-light">
        <div className="container-fluid">
          <a className="navbar-brand fw-bold" href="#">CompanyName</a>
          <div>
            <button className="btn btn-outline-success me-2" onClick={handleLoginClick}>Login</button>
            <button className="btn btn-outline-success me-2" onClick={handleRegisterClick}>Register</button>
            <button className="btn btn-outline-success" onClick={handlePaymentClick}>Payment</button>
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

        {step === 1 && <Register onNext={handleRegisterNext} />}
        {step === 2 && <Payment onNext={handlePaymentNext} />}
        {step === 3 && <Login onSuccess={handleLoginSuccess} />}
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
