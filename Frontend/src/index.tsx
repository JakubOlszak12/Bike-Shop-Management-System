/* istanbul ignore file */
import "~/styles/index.scss";
import { StrictMode, useEffect, useState } from "react";
import reactDOMClient from "react-dom/client";
import { App } from "~/components/App";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import React from "react";
import { ProtectedRoute } from "./components/ProtectedRoute";
import { NavBar } from "./components/Navbar";
import { Login } from "./components/Login";
import { NotFound } from "./components/NotFound";
import { Register } from "./components/Register";
const rootContainer = document.createElement("div");
document.body.appendChild(rootContainer);
const root = reactDOMClient.createRoot(rootContainer);

const isAuthenticatedFromCookie = () => {
    const token = document.cookie.split('; ').find(row => row.startsWith('token='));
    return !!token; // Convert to boolean
};

const AppContainer = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(isAuthenticatedFromCookie());

    useEffect(() => {
        setIsAuthenticated(isAuthenticatedFromCookie());
    }, []);


    return (
        <React.StrictMode>
            <BrowserRouter>
            <NavBar isAuthenticated={isAuthenticated} />
             <Routes>
                <Route path="/" element={<App/>}/>
                <Route path="/login" element={<Login />}/>
                <Route path="*" element={<NotFound />} />
                <Route path="/register" element={<Register/>}/>
             </Routes>
            </BrowserRouter>
        </React.StrictMode>
    );

};

root.render(
    <AppContainer />
);


