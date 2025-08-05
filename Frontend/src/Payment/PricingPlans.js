import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Check } from 'lucide-react';
import './PricingPlans.css';

const PricingPlans = () => {
    const navigate = useNavigate();

    const plans = [
        {
            id: 'basic',
            name: 'Basic',
            price: '$1,990/m',
            description: 'One request at a time. Pause or cancel anytime.',
            isPopular: false,
            features: [
                'One request at a time',
                'Average 48 hour delivery',
                'Unlimited brands',
                'Unlimited users',
                'Easy credit-card payments',
                'Pause or cancel anytime',
            ],
            buttonText: 'Get Started',
            buttonClass: 'btn-primary',
        },
        {
            id: 'pro',
            name: 'Pro',
            price: '$2,990/m',
            description: 'Two request at a time. Pause or cancel anytime.',
            isPopular: false,
            features: [
                'Two request at a time',
                'Average 48 hour delivery',
                'Unlimited brands',
                'Unlimited users',
                'Easy credit-card payments',
                'Pause or cancel anytime',
            ],
            buttonText: 'Get Started',
            buttonClass: 'btn-primary',
        },
        {
            id: 'basicFramer',
            name: 'Basic + Framer',
            price: '$3,990/m',
            description: 'For those in need of design and front-end development.',
            isPopular: true,
            features: [
                'One request at a time',
                'Framer development',
                'Average 72 hour delivery',
                'Support and maintenance',
                'Pause or cancel anytime',
            ],
            buttonText: 'Get Started',
            buttonClass: 'btn-secondary',
        },
    ];

    const handleGetStarted = (planId, price) => {
        // Extract numbers only, convert to paise (multiply by 100)
        const amount = Number(price.replace(/[^0-9]/g, '')) * 100;
        // Navigate to payment page passing amount & planId via state
        navigate('/payment', { state: { amount, planId } });
    };

    // ...rest of your code same, just update button onClick:

    return (
        <div className="pricing-container">
            <div className="plans-grid">
                {plans.map((plan) => (
                    <div key={plan.id} className={`plan-card ${plan.isPopular ? 'popular' : ''}`}>
                        {plan.isPopular && <div className="popular-badge">Most Popular</div>}

                        <div className="plan-header">
                            <h3>{plan.name}</h3>
                            <div className="price">{plan.price}</div>
                            <p className="description">{plan.description}</p>
                        </div>

                        <div className="features-list">
                            {plan.features.map((feature, i) => (
                                <div key={i} className="feature-item">
                                    <Check className={`check-icon ${plan.isPopular ? 'popular-check' : 'normal-check'}`} />
                                    <span>{feature}</span>
                                </div>
                            ))}
                        </div>

                        <button
                            onClick={() => handleGetStarted(plan.id, plan.price)}
                            className={`btn ${plan.buttonClass}`}
                        >
                            {plan.buttonText}
                        </button>
                    </div>
                ))}
            </div>

            {/* Extra options remain same */}
            {/* ... */}
        </div>
    );
};

export default PricingPlans;
