import React from "react";
import "./App.css";

function App() {
  return (
    <div className="container my-5">
      {/* Top Section */}
      <section className="text-start mb-4">
        <h2 className="fw-bold">Memberships plans.</h2>
        <p>Choose a plan that's right for you…</p>
      </section>

      {/* Plans Section */}
      <div className="row">
        {/* Plan 1 */}
        <div className="col-md-4 mb-4">
          <div className="card h-100 border-primary">
            <div className="card-body">
              <h5 className="card-title fw-bold">Starter</h5>
              <h6 className="card-subtitle mb-2 text-muted">Free</h6>
              <ul className="list-group list-group-flush my-3">
                <li className="list-group-item">Get started with our free plan</li>
                <li className="list-group-item">Access limited content</li>
                <li className="list-group-item">No support</li>
              </ul>
              <button className="btn btn-outline-primary w-100">Get Started</button>
            </div>
          </div>
        </div>

        {/* Plan 2 */}
        <div className="col-md-4 mb-4">
          <div className="card h-100 border-success">
            <div className="card-body">
              <h5 className="card-title fw-bold">Pro</h5>
              <h6 className="card-subtitle mb-2 text-muted">₹199/month</h6>
              <ul className="list-group list-group-flush my-3">
                <li className="list-group-item">Unlock all courses</li>
                <li className="list-group-item">Download materials</li>
                <li className="list-group-item">Email support</li>
              </ul>
              <button className="btn btn-success w-100">Subscribe Now</button>
            </div>
          </div>
        </div>

        {/* Plan 3 */}
        <div className="col-md-4 mb-4">
          <div className="card h-100 border-dark">
            <div className="card-body">
              <h5 className="card-title fw-bold">Premium</h5>
              <h6 className="card-subtitle mb-2 text-muted">₹499/month</h6>
              <ul className="list-group list-group-flush my-3">
                <li className="list-group-item">All Pro benefits</li>
                <li className="list-group-item">1:1 Mentorship</li>
                <li className="list-group-item">Live support</li>
              </ul>
              <button className="btn btn-dark w-100">Go Premium</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
