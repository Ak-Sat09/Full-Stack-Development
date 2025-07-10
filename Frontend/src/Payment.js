 import React, { useState } from "react";

const Payment = ({ onNext }) => {
  const [email, setEmail] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch(
        `http://localhost:8080/api/payment/success?email=${encodeURIComponent(
          email
        )}`,
        {
          method: "POST",
        }
      );

      const data = await res.json();

      if (!res.ok || !data.success) {
        alert("❌ Payment Failed: " + data.message);
        return;
      }

      alert("✅ Payment Successful: " + data.message);
      onNext();
    } catch (err) {
      alert("🚫 Server Error");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Payment Step</h2>
      <label>Email</label>
      <input
        name="email"
        type="email"
        className="form-control"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
      />
      <button
        type="submit"
        className="form-control"
        style={{ backgroundColor: "black", color: "white" }}
      >
        Submit Payment Email
      </button>
    </form>
  );
};

export default Payment;
