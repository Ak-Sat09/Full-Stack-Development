import React, { useState } from "react";

const Register = ({ onNext }) => {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    referredByCode: ""
  });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch("http://localhost:8080/api/v1/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });

      const data = await res.text();

      if (res.ok) {
        alert(data); // show full message including referral code
        // Extract referral code from message string
        const match = data.match(/Referral Code:\s*(\d{4})/);
        const referralCode = match ? match[1] : "";
        onNext(referralCode); // Pass referral code back to parent
      } else {
        alert("Error: " + data);
      }
    } catch (err) {
      alert("Server Error");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <h2>Register</h2>

        <label>Full Name</label>
        <input
          name="name"
          type="text"
          className="form-control"
          value={form.name}
          onChange={handleChange}
          required
        />

        <label>Email</label>
        <input
          name="email"
          type="email"
          className="form-control"
          value={form.email}
          onChange={handleChange}
          required
        />

        <label>Password</label>
        <input
          name="password"
          type="password"
          className="form-control"
          value={form.password}
          onChange={handleChange}
          required
        />

        <label>Referral Code (Optional)</label>
        <input
          name="referredByCode"
          type="text"
          className="form-control"
          value={form.referredByCode}
          onChange={handleChange}
        />
      </div>

      <button
        type="submit"
        className="form-control"
        style={{ backgroundColor: "black", color: "white" }}
      >
        Continue to Payment
      </button>
    </form>
  );
};

export default Register;
