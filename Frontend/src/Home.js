 import React from "react";

const Home = () => {
  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Welcome to the Dashboard</h1>

      <div className="card p-4 shadow">
        <h3>Hello, User!</h3>
        <p>You have successfully logged in. This is your home page.</p>

        <hr />
        <p>
          You can now access protected content, view your profile, or explore
          more features from here.
        </p>

        <button
          className="btn btn-dark"
          onClick={() => {
            localStorage.clear();
            window.location.href = "/";
          }}
        >
          Logout
        </button>
      </div>
    </div>
  );
};

export default Home;
