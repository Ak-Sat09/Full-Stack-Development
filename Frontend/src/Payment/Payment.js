import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

const PaymentPage = () => {
    const location = useLocation();
    const navigate = useNavigate();

    const { amount, planId } = location.state || {};

    if (!amount) {
        navigate('/');
        return null;
    }

    const handlePayment = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/api/payments/create', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ amount }),
            });

            const data = await response.json();

            if (data.status === 'SUCCESS') {
                const options = {
                    key: 'rzp_test_qjbBqMLEcOwmiE',
                    amount: data.data.amount,
                    currency: data.data.currency,
                    name: 'Your Company',
                    order_id: data.data.orderId,
                    handler: async (response) => {
                        try {
                            const verifyResponse = await fetch(
                                `http://localhost:8080/api/payments/verify?orderId=${response.razorpay_order_id}&paymentId=${response.razorpay_payment_id}&signature=${response.razorpay_signature}`,
                                {
                                    method: 'POST',
                                    headers: { 'Content-Type': 'application/json' },
                                }
                            );
                            const result = await verifyResponse.json();
                            alert(`${result.message} for plan: ${planId}`);

                            // Redirect to home after success
                            navigate('/');
                        } catch {
                            alert('Payment verification failed!');
                        }
                    },
                    prefill: {
                        email: 'customer@example.com',
                    },
                    theme: {
                        color: '#3399cc',
                    },
                };

                const rzp = new window.Razorpay(options);
                rzp.open();
            } else {
                alert('Failed to create order');
            }
        } catch {
            alert('Error creating order');
        }
    };

    return (
        <div style={styles.container}>
            <h1 style={styles.heading}>Pay with Razorpay</h1>
            <p>Plan Selected: <b>{planId}</b></p>
            <p>Amount: ₹{amount / 100}</p>

            <button id="payBtn" style={styles.button} onClick={handlePayment}>
                Pay Now
            </button>


        </div>
    );
};

const styles = {
    container: {
        fontFamily: 'Arial, sans-serif',
        backgroundColor: '#f0f4f8',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        height: '100vh',
        margin: 0,
        color: '#333',
    },
    heading: {
        color: '#3399cc',
        marginBottom: '30px',
    },
    button: {
        backgroundColor: '#3399cc',
        color: 'white',
        fontSize: '18px',
        padding: '12px 30px',
        border: 'none',
        borderRadius: '6px',
        cursor: 'pointer',
        transition: 'background-color 0.3s ease',
        boxShadow: '0 4px 8px rgba(51, 153, 204, 0.3)',
    },
};

export default PaymentPage;
