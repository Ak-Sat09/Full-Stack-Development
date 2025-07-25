import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

// Display a node and its children recursively
const ReferralNode = ({ node }) => {
  return (
    <div className="ms-4 mt-2">
      <div className="p-2 border rounded bg-light">{node.name}</div>
      {node.children && node.children.map((child, index) => (
        <ReferralNode key={index} node={child} />
      ))}
    </div>
  );
};

const Home = ({ referralCode }) => {
  const [tree, setTree] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const fetchReferralTree = async () => {
    if (!referralCode) {
      setError("Referral code not available");
      return;
    }

    setLoading(true);
    setError("");
    try {
      const res = await fetch(`http://localhost:8080/${referralCode}`);
      if (res.ok) {
        const data = await res.json();
        setTree(data);
      } else {
        setError("Failed to fetch referral tree");
      }
    } catch (err) {
      setError("Server error");
    }
    setLoading(false);
  };

  useEffect(() => {
    if (referralCode) {
      fetchReferralTree();
    }
  }, [referralCode]);

  return (
    <div className="container my-4">
      <h3 className="mb-3">Referral Tree</h3>

      <button className="btn btn-dark mb-3" onClick={fetchReferralTree}>
        Load My Referral Tree
      </button>

      {loading && <p className="text-secondary">Loading...</p>}
      {error && <p className="text-danger">{error}</p>}

      {tree && (
        <div className="border p-3 rounded bg-white">
          <ReferralNode node={tree} />
        </div>
      )}
    </div>
  );
};

export default Home;
