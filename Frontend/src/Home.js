import React, { useState } from "react";

const ReferralNode = ({ node }) => {
  const [expanded, setExpanded] = useState(false);
  const hasChildren = node.children && node.children.length > 0;

  return (
    <div style={{ marginLeft: "20px", marginTop: "5px" }}>
      <div
        onClick={() => hasChildren && setExpanded(!expanded)}
        style={{
          cursor: hasChildren ? "pointer" : "default",
          padding: "6px 10px",
          borderRadius: "6px",
          userSelect: "none",
          display: "inline-block",
          backgroundColor: expanded ? "#000" : "#fff",
          color: expanded ? "#fff" : "#000",
          fontWeight: expanded ? "bold" : "normal",
          transition: "background-color 0.3s, color 0.3s",
        }}
        title={hasChildren ? (expanded ? "Collapse" : "Expand") : ""}
      >
        {node.name}
      </div>
      {expanded && hasChildren && (
        <div>
          {node.children.map((child) => (
            <ReferralNode key={child.referralCode || child.name} node={child} />
          ))}
        </div>
      )}
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
    setTree(null);

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

  return (
    <div style={{ maxWidth: "700px", margin: "20px auto", fontFamily: "Arial, sans-serif" }}>
      <h2>Welcome to Home</h2>
      <button
        className="btn btn-primary mb-3"
        onClick={fetchReferralTree}
        style={{ backgroundColor: "#000", borderColor: "#000" }}
      >
        Show My Referral Tree
      </button>

      {loading && <p>Loading...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}

      {tree && (
        <div
          style={{
            backgroundColor: "#fff",
            padding: "1rem",
            borderRadius: "8px",
            boxShadow: "0 0 10px rgba(0,0,0,0.1)",
            color: "#000",
          }}
        >
          <ReferralNode node={tree} />
        </div>
      )}
    </div>
  );
};

export default Home;
