//import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect} from 'react';
import Register from './components/Register/Register.js'
//import Login from './components/Login/Login.js';
import LoginForm from './components/Login/LoginForm.js'
import UserPage from './components/User/UserPage.js'
import AdminPage from './components/User/AdminPage.js'
import ProtectedRoute from "./ProtectedRoute.js"
import { BrowserRouter as Router, Route, Routes, Navigate} from 'react-router-dom';

function App() {
  //const [token, setToken]=useState(localStorage.getItem('token'));
  //localStorage.getItem("user", JSON.stringify(user));
  const user = JSON.parse(localStorage.getItem("user"))
  //console.log(user.user.role);
  //<Route path="/admin" element={(user && user.user.role==="admin") ? <AdminPage /> : <Navigate to="/login" replace/>}/>
  //<Route path="/user" element={user ? <UserPage /> : <Navigate to="/login" replace />}/>

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/unauthorized" element={<div>Not allowed.</div>} />
        <Route path="/register" element={<Register />} /> {}
        <Route path="/user" element={<ProtectedRoute allowedRoles={["user"]}><UserPage /></ProtectedRoute> }/>
        <Route path="/admin" element={<ProtectedRoute allowedRoles={["admin"]}><AdminPage /></ProtectedRoute>}/>
      </Routes>
    </Router>
  );
}

export default App;
