import React from 'react';
import Login from './Login.js';
import { Link } from 'react-router-dom'; 
import './Login.css';

const LoginForm = () => {
    return (
        <div className="container-login">
            <div className="login-form">
                <h2 className="login-title">Login</h2>
                <Login />
                <div className="register-link">
                    <p> Create an account<Link to="/register"> here</Link></p>
                </div>
            </div>
        </div>
    );
};

export default LoginForm;
