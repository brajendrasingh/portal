import './App.css';
import React, { useState } from 'react';
import axios from 'axios';

function App() {
  const apiUrl = process.env.REACT_APP_API_URL;
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log('Logging in with:', { username, password });
    try {
      const res = await axios.post(`${apiUrl}/auth/login`, {
        email: username,
        password: password,
        rememberMe: "true"
      }, {
        headers: {
          "Content-Type": "application/json"
        }
      });
      console.log("Login success: ", res.data);
    } catch (err) {
      console.error(err.response?.data?.message || 'Something went wrong');
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>Login</h2>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default App;
