import { useEffect, useState, type ReactElement } from "react";
import { NavLink, useNavigate } from "react-router-dom";

interface NavBarProps {
    isAuthenticated: boolean;
}

export function NavBar({ isAuthenticated }: NavBarProps): ReactElement {
    const navigation = useNavigate();
    const [cartItemCount, setCartItemCount] = useState(0);
    
    const handleLogout = () => {
        // Clear the token from cookies
        document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        window.location.reload();
    };

    const handleClients = () => {
        // Redirect to /clients
        navigation("/clients");
    };

    useEffect(() => {
        // Retrieve cart items count from local storage
        const cartItems = JSON.parse(localStorage.getItem("cart") || "[]");
        setCartItemCount(cartItems.length);
    }, []);


    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <a className="navbar-brand" href="/">Bike shop</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
                <ul className="navbar-nav">
                    <li className="nav-item">
                            <NavLink
                                to="/"
                                className="nav-item"
                            >
                                <span className="nav-link">Products</span>
                            </NavLink>
                    </li>
                </ul>

                <ul className="navbar-nav">
                    <li className="nav-item">
                        {isAuthenticated ? (
                            <NavLink
                                to="/login"
                                className="nav-item"
                                onClick={handleLogout} // Call handleLogout function on click
                            >
                                <a className="nav-link" href="/login">Logout</a>
                            </NavLink>
                        ) : (
                            <NavLink
                                to="/login"
                                className="nav-item"
                            >
                                <span className="nav-link">Login</span>
                            </NavLink>
                        )}
                    </li>
                    <li className="nav-item">
                        {isAuthenticated && (
                            <NavLink
                            to="/profile"
                            className="nav-item"
                        >
                            <span className="nav-link">My Profile</span>
                        </NavLink>
                        )}
                    </li>
                    <li className="nav-item position-relative">
                        
                    <NavLink
                                to="/cart"
                                className="nav-item"
                            >
                                <span className="nav-link">
                                <i className="fa-solid fa-cart-shopping"></i>
                                <span className="badge badge-pill bg-secondary cart-badge">{cartItemCount}</span>
                            </span>
                            </NavLink>
                    </li>
                </ul>
            </div>
        </nav>
    );
}
