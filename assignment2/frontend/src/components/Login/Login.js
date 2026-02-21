import React, {useState} from 'react';
import {useNavigate} from 'react-router-dom';
//import "./Login.css";
import {loginUser} from '../../api/login-api.js';
import axios from 'axios';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const [success, setSuccess] = useState('');
  const [error, setError] = useState('');
  //const inputEmail = localStorage.getItem("email");
  //const inputPassword = localStorage.getItem("password");

  const handleLogin = async (event) => {
    event.preventDefault();
    
    //const response = await loginUser(email, password);
    try {
      const response = await axios.get("/user/api/users/me", {
        auth: {
          username: email,
          password: password
        }
      });

      const userData = response.data;
      localStorage.setItem("email", email);
      localStorage.setItem("password", password);
      localStorage.setItem("user", JSON.stringify(userData));

      console.log("You logged in", userData);
      console.log(userData.role);
      //localStorage.setItem("user", JSON.stringify(userData));
      //localStorage.setItem("user", JSON.stringify(userData.user)); // includes role
      //window.location.relo
      //const user = userData["user:"]
      if(userData.role === "user"){
        navigate("/user");
      } else if (userData.role === "admin") {
        navigate("/admin");
      } else {
        setError("ERROR: User role unknown");
      } 
    } catch (err) {
      setError("Invalid credentials");
      console.error(err);
    }
  };
  
  //fr fr no cap this is crap code 

  return( 
    <form onSubmit={handleLogin}>
      <div className="login-field">
        <label htmlFor="email">Email:</label>
        <input 
          type="email"
          id="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="Enter the Email address"
          required
         />
      </div>
      <div className="login-field">
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Enter super strong password"
          required
        />
      </div>
      {success && <div className="success">{success}</div>}
      {error && <div className="error">{error}</div>}
      <button type="submit" className="button-login">Login</button>
    </form> 
  );
};

export default Login;
