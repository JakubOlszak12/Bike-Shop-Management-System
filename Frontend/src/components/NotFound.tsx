import type { ReactElement } from "react";
import { Link } from "react-router-dom";


export function NotFound(): ReactElement {
    return (
        <div className="container text-center mt-5">
            <h1 className="display-4">404</h1>
            <p className="lead">Oops! Page not found.</p>
            <p>Sorry, the page you are looking for might have been removed, had its name changed, or is temporarily unavailable.</p>
            <Link to="/" className="btn btn-primary">Go to Home</Link>
        </div>
    );
}
