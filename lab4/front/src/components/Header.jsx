import React from 'react';

const Header = ({ username, onLogout }) => (
    <div className="header">
        <h1>Welcome, {username}!</h1>
        <button onClick={onLogout} className="logout-button">
            Logout
        </button>
    </div>
);

export default Header;

