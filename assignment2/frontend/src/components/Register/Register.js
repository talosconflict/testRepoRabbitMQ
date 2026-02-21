import React, {useState} from 'react';
import {registerUser} from "../../api/login-api.js";
import './Register.css'
//I hate frontend development, why can't people just use a terminal
//Can't we just assume the users are registered beforehand? 

const Register = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [name, setName] = useState(''); //forgor the name
  const [success, setSuccess] = useState('');
  const [error, setError] = useState('');

  const handleRegister = async (event) => { //typo in async - shitty error message - awesome JS 
    event.preventDefault();
    
    if(password !== confirmPassword) {
      setError("Passwords do not match, try again lol, pleb");
      return;
    }
    
    const response = await registerUser(email, name, password);
    if(response.success){
      setSuccess("Registration successful!");
      setError(''); //i mean, success == 1 implies error == 0 - logic 
    } else {
      setSuccess('');
      setError("ERROR: Registration failed:", response.message);
    }
  };

  //worst part immaginable incoming 
  return (
    <form onSubmit={handleRegister}>
      <div className="login-field">
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(e)=>setEmail(e.target.value)}
          placeholder="Enter email"
          required
        />
      </div>
      <div className="login-field">
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(e)=>setName(e.target.value)}
          placeholder="Enter your bloody name"
          required
        />
      </div>
      <div className="login-field">
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={(e)=>setPassword(e.target.value)}
          placeholder = "Enter password - super secure"
          required
          />
      </div>
      <div className="login-field">
        <label htmlFor="confirmPassword">Confirm Password bro:</label>
        <input
          type="password"
          id="confirmPassword"
          value={confirmPassword}
          onChange={(e)=>setConfirmPassword(e.target.value)}
          placeholder="Please confirm your password"
          required 
        />
      </div>
      {success && <div className="success">{success}</div>}
      {error && <div className="errors">{error}</div>}
      <button type="submit" className="button-login">Registration</button>
    </form>
  ); //honestly i wanna leave it as return();
};

export default Register;
