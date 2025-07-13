import React, { useState } from "react";

const Login = ({ onSuccess }) => {
  const [loginForm, setLoginForm] = useState({ email: "", password: "" });

  const handleChange = (e) =>
    setLoginForm({ ...loginForm, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch("http://localhost:8080/api/v1/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(loginForm),
      });

      const data = await res.json();

      if (res.ok) {
        alert("Login Success: " + data.message);
        // You can access referralCode here
        console.log("Referral Code:", data.referralCode);

        // Pass referralCode back to parent or store in state
        onSuccess(data.referralCode);
      } else {
        alert("Error: " + (data.message || "Invalid email or password"));
      }
    } catch (err) {
      alert("Server Error");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Login</h2>
      <label>Email</label>
      <input
        name="email"
        type="email"
        className="form-control"
        value={loginForm.email}
        onChange={handleChange}
        required
      />

      <label>Password</label>
      <input
        name="password"
        type="password"
        className="form-control"
        value={loginForm.password}
        onChange={handleChange}
        required
      />

      <button
        type="submit"
        className="form-control"
        style={{ backgroundColor: "black", color: "white" }}
      >
        Login
      </button>
    </form>
  );
};

export default Login;
