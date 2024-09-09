import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

export function Register() {
  const [errorMessage, setErrorMessage] = useState('');
  const navigation = useNavigate();

  function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const username = formData.get('username') as string;
    const email = formData.get('email') as string;
    const password = formData.get('password') as string;
    const confirmPassword = formData.get('confirmPassword') as string;

    // Simple data validation
    if (!username.trim() || !email.trim() || !password.trim() || !confirmPassword.trim()) {
      setErrorMessage('Please fill in all fields');
      return;
    }

    if (password !== confirmPassword) {
      setErrorMessage('Passwords do not match');
      return;
    }

    // Send your registration request with username, email, and password
    fetch('http://localhost:8080/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, email, password }),
    })
      .then((response) => {
        if (!response.ok) {
          // If the response has an error, we try to extract the error message from the response body
          return response.json().then((errorData) => {
            console.log(errorData)
            throw new Error(errorData.message);
          });
        }
        return response.json();
      })
      .then((data) => {
        // Handle successful registration
        console.log(data);
        navigation('/');
      })
      .catch((error) => {
        // Handle errors
        setErrorMessage(error.message);
      });
  }

  return (
    <>
      <div className="container mt-5">
        <h2 style={{ textAlign: 'center' }}>Register</h2>
        {errorMessage && <div style={{ color: 'red', textAlign:'center' }}>{errorMessage}</div>}
        <Form onSubmit={handleSubmit} className="w-50 mx-auto">
          <Form.Group controlId="formUsername">
            <Form.Label>Username</Form.Label>
            <Form.Control type="text" name="username" placeholder="Enter your username" />
          </Form.Group>

          <Form.Group controlId="formEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control type="email" name="email" placeholder="Enter your email" />
          </Form.Group>

          <Form.Group controlId="formPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" name="password" placeholder="Enter your password" />
          </Form.Group>

          <Form.Group controlId="formConfirmPassword">
            <Form.Label>Confirm Password</Form.Label>
            <Form.Control type="password" name="confirmPassword" placeholder="Confirm your password" />
          </Form.Group>

          <br />
          <div className="text-center">
          
            <Button variant="primary" type="submit">
              Register
            </Button>
            <p className="mt-3">Already have an account? <a href="/login">Login</a></p>
          </div>
        </Form>
      </div>
    </>
  );

}
