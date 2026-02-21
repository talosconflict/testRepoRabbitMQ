import React from 'react';
import './App.css';
import { BrowserRouter, Route,Switch } from 'react-router-dom';
import Login from '../Login/Login.js';

function App() {
  return (
    <div className="wrapper">
      <h1>Application</h1>
      <BrowserRouter>
        <Switch>
          <Route path="/login">
            <Dashboard />
          </Route>
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
