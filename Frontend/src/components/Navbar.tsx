import { useEffect, useState, type ReactElement } from "react";
import { NavLink, useNavigate } from "react-router-dom";

interface NavBarProps {
    isAuthenticated: boolean;
}

interface Category {
    id: number;
    name: string;
}

export function NavBar({ isAuthenticated }: NavBarProps): ReactElement {
    const navigation = useNavigate();
    const [cartItemCount, setCartItemCount] = useState(0);
    const [categories, setCategories] = useState<Category[]>([]);
    const handleLogout = () => {
        // Clear the token from cookies
        document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        navigation("/login")
        window.location.reload();
    };

    useEffect(() => {
        // Retrieve cart items count from local storage
        const cartItems = JSON.parse(localStorage.getItem("cart") || "[]");
        setCartItemCount(cartItems.length);

        fetch('http://localhost:8080/api/categories/getAll')
          .then(response => {
            if (!response.ok) {
              throw new Error('Failed to fetch categories');
            }
            return response.json();
          })
          .then((data: Category[]) => {
            setCategories(data); // Update categories state with fetched data
          })
          .catch(error => {
            console.error('Error fetching categories:', error.message);
          });
    }, []);

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <a className="navbar-brand" href="/">Bike shop</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    {categories.map(category => (
                        <li className="nav-item" key={category.id}>
                            <NavLink
                                to={`/categories/${category.name}`}
                                className="nav-link"
                            >
                                {category.name}
                            </NavLink>
                        </li>
                    ))}
                </ul>

                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        {isAuthenticated ? (
                            <NavLink
                                to="/login"
                                className="nav-item"
                                onClick={handleLogout} // Call handleLogout function on click
                            >
                                <span className="nav-link">Logout</span>
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
