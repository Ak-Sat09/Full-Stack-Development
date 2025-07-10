 import React, { useState } from "react";

const Register = ({onNext}) => {
  const [form, setForm] = useState({ name: "", email: "", password: "" });

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

      if (res.ok) {
        alert("Registered Successfully");
        onNext();
      } else {
        const error = await res.text();
        alert(" Error: " + error);
      }
    } catch (err) {
      alert("Server Error");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <h2>Personal Information</h2>
        <label>Full Name</label>
        <input name="name" type="text" className="form-control" value={form.name} onChange={handleChange} required />
        <label>Email</label>
        <input name="email" type="email" className="form-control" value={form.email} onChange={handleChange} required />
      </div>

      <div className="mb-3">
        <label>Password</label>
        <input name="password" type="password" className="form-control" value={form.password} onChange={handleChange} required />
      </div>

      <button type="submit" className="form-control" style={{ backgroundColor: "black", color: "white" }}>
        Continue to Payment
      </button>
    </form>
  );
};

export default Register;
