CREATE TABLE loan_applications (
    id UUID PRIMARY KEY,
    applicant_name VARCHAR(255) NOT NULL,
    monthly_income NUMERIC(19, 4) NOT NULL,
    monthly_debt_payments NUMERIC(19, 4) NOT NULL,
    credit_score INT NOT NULL,
    requested_amount NUMERIC(19, 4) NOT NULL,
    status VARCHAR(50) NOT NULL,
    rejection_reason VARCHAR(500),
    created_at TIMESTAMP NOT NULL
);