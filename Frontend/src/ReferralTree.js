import React, { useEffect, useState } from "react";

/* ─── small tree node component ─── */
const TreeNode = ({ node }) => {
    const [open, setOpen] = useState(true); // toggle children

    return (
        <li>
            <span
                style={{ cursor: "pointer", fontWeight: "bold" }}
                onClick={() => setOpen(!open)}
            >
                {node.name} – <em>{node.referralCode}</em>
            </span>

            {open && node.children?.length > 0 && (
                <ul style={{ marginLeft: "1rem", listStyle: "circle" }}>
                    {node.children.map((child) => (
                        <TreeNode key={child.referralCode} node={child} />
                    ))}
                </ul>
            )}
        </li>
    );
};

/* ─── main component ─── */
const ReferralTree = () => {
    const [codeInput, setCodeInput] = useState("");
    const [tree, setTree] = useState(null);
    const [error, setError] = useState("");

    const fetchTree = async () => {
        if (!codeInput) return;
        try {
            const res = await fetch(`http://localhost:8080/${codeInput}`);
            if (!res.ok) {
                setError("Referral code not found");
                setTree(null);
                return;
            }
            const data = await res.json();
            setTree(data);
            setError("");
        } catch {
            setError("Server error");
            setTree(null);
        }
    };

    /* auto‑fetch when code changes (optional) */
    // useEffect(() => { if (codeInput) fetchTree(); }, [codeInput]);

    return (
        <div className="card p-4 shadow">
            <h3>Referral Network</h3>

            {/* input */}
            <div className="input-group mb-3">
                <input
                    className="form-control"
                    placeholder="Enter root referral code"
                    value={codeInput}
                    onChange={(e) => setCodeInput(e.target.value)}
                />
                <button className="btn btn-dark" onClick={fetchTree}>
                    Load
                </button>
            </div>

            {/* error */}
            {error && <p style={{ color: "red" }}>{error}</p>}

            {/* tree */}
            {tree && (
                <ul style={{ listStyle: "circle" }}>
                    <TreeNode node={tree} />
                </ul>
            )}
        </div>
    );
};

export default ReferralTree;
