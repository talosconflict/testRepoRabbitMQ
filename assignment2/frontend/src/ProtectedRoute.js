import { Navigate } from "react-router-dom";

export default function ProtectedRoute({ children, allowedRoles }) {
    const user = JSON.parse(localStorage.getItem("user"));

    // Not logged in at all
    if (!user) {
        return <Navigate to="/login" />;
    }

    // Logged in but role is not allowed
    if (!allowedRoles.includes(user.role)) {
        return <Navigate to="/unauthorized" />; // or /login
    }

    // Allowed
    return children;
}

