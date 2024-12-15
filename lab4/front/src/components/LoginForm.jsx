import React, { useState } from 'react';
import { TextField, Button, Typography } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import { loginUser } from '../slices/AuthSlice';
import { useHistory } from 'react-router-dom';
import '../style/Form.css';

const LoginForm = ({ onSwitch }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();
    const history = useHistory();

    const { loading, error } = useSelector((state) => state.auth);

    const handleLogin = () => {
        dispatch(loginUser({ username, password }))
            .unwrap()
            .then(() => {
                history.push('/main');
            })
            .catch((err) => {
                console.error('Login failed:', err);
            });
    };

    return (
        <div className="form-container">
            <Typography variant="h4">Login</Typography>

            <TextField
                label="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                className="full-width"
                variant="outlined"
            />
            <TextField
                label="Password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="full-width"
                variant="outlined"
            />
            <Button
                variant="contained"
                color="primary"
                size="large"
                onClick={handleLogin}
                className="full-width"
                disabled={loading}
            >
                {loading ? 'Logging in...' : 'Login'}
            </Button>
            {error && <Typography color="error">{error}</Typography>}
            <Typography>
                Don't have an account?{' '}
                <Button variant="text" color="primary" size="small" onClick={onSwitch}>
                    Register
                </Button>
            </Typography>
        </div>
    );
};

export default LoginForm;
