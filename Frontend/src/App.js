import React, { useState } from "react";
import Register from "./Register";
import Login from "./Login";
import Payment from "./Payment";
import Home from "./Home";
import "./App.css";

function App() {
  const [step, setStep] = useState(3); // Default: login
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userReferralCode, setUserReferralCode] = useState("");

  // 1️⃣ After Register → Go to Payment and save referral code
  const handleRegisterNext = (referralCode) => {
    setUserReferralCode(referralCode); // store referral code from Register.js
    setStep(2); // go to payment
  };

  // 2️⃣ After Payment → Go to Login
  const handlePaymentNext = () => {
    setStep(3);
  };

  // 3️⃣ On Login success → Go to Home and store referralCode
  const handleLoginSuccess = (referralCode) => {
    setIsLoggedIn(true);
    setUserReferralCode(referralCode);
    setStep(4);
  };

  // 4️⃣ If payment not done → Redirect to Payment
  const handleRedirectToPayment = () => {
    setStep(2);
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
        {step === 1 && !isLoggedIn && (
          <Register onNext={handleRegisterNext} />
        )}
        {step === 2 && !isLoggedIn && <Payment onNext={handlePaymentNext} />}
        {step === 3 && !isLoggedIn && (
          <Login
            onSuccess={handleLoginSuccess}
            onRedirectToPayment={handleRedirectToPayment}
          />
        )}
        {step === 4 && isLoggedIn && <Home referralCode={userReferralCode} />}
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
