import { Navigate } from 'react-router-dom';

export const ProtectedRoute = ({ children, ...props } : any) => {
    const token = document.cookie.split('; ').find(row => row.startsWith('token='));

    if (!token) {
        return <Navigate to="/login" />;
    }

    return children;
};
