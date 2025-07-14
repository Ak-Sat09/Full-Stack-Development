import React, { useState, useEffect } from "react";

// Display a node and its children recursively
const ReferralNode = ({ node }) => {
  return (
    <div style={{ marginLeft: "20px" }}>
      <div>{node.name}</div>
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
    <div style={{ padding: "20px" }}>
      <h3>Referral Tree</h3>
      <button onClick={fetchReferralTree}>Load My Referral Tree</button>

      {loading && <p>Loading...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}

      {tree && <ReferralNode node={tree} />}
    </div>
  );
};

export default Home;
