import React, { useState } from "react";
import Register from "./Register";
import Login from "./Login";
import Home from "./Home";
import Payment from "./Payment";
import "./App.css";

function App() {
  const [step, setStep] = useState(3); // default to login
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userReferralCode, setUserReferralCode] = useState("");

  const handleRegisterNext = () => {
    // after registration, just go to login (no forced payment)
    setStep(3);
  };

  const handlePaymentNext = () => {
    // optional: if payment done, go to login
    setStep(3);
  };

  const handleLoginSuccess = (referralCode) => {
    setIsLoggedIn(true);
    setUserReferralCode(referralCode);
    alert("User logged in successfully");
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg bg-light">
        <div className="container-fluid">
          <a className="navbar-brand fw-bold" href="#">
            CompanyName
          </a>
          <div>
            {!isLoggedIn && (
              <>
                <button
                  className="btn btn-outline-success me-2"
                  onClick={() => setStep(3)}
                >
                  Login
                </button>
                <button
                  className="btn btn-outline-success"
                  onClick={() => setStep(1)}
                >
                  Register
                </button>
              </>
            )}
          </div>
        </div>
      </nav>

      <div className="container mt-5">
        {!isLoggedIn && step === 1 && <Register onNext={handleRegisterNext} />}
        {!isLoggedIn && step === 2 && <Payment onNext={handlePaymentNext} />}
        {!isLoggedIn && step === 3 && <Login onSuccess={handleLoginSuccess} />}
        {isLoggedIn && <Home referralCode={userReferralCode} />}
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
