import React, { useEffect, useState } from 'react';
import Header from './components/Header';
import InputForm from './components/InputForm';
import CanvasArea from './components/Canvas';
import ResultsTable from './components/ResultsTable';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import { logout } from './slices/AuthSlice';
import {sendCoordinates, fetchUserResults, clearResults} from './slices/ResultSlice';
import './style/Main.css';

const MainPage = () => {
    const [x, setX] = useState('0');
    const [y, setY] = useState('');
    const [r, setR] = useState('1');

    const dispatch = useDispatch();
    const history = useHistory();

    const authUsername = useSelector((state) => state.auth.username);
    const registerUsername = useSelector((state) => state.register.username);
    const username = authUsername || registerUsername;

    const authUserId = useSelector((state) => state.auth.userId);
    const registerUserId = useSelector((state) => state.register.userId);
    const userId = authUserId || registerUserId;

    const results = useSelector((state) => state.results.results);
    const loading = useSelector((state) => state.results.loading);


    useEffect(() => {
        if (userId) {
            dispatch(fetchUserResults(userId));
        }
    }, [dispatch, userId]);

    const handleLogout = () => {
        dispatch(logout());
        dispatch(clearResults());
        history.push('/');
    };

    const handleCanvasClick = (point) => {
        if (userId) {
            dispatch(sendCoordinates({ x: point.x, y: point.y, r, userId }));
        } else {
            console.log("UserId is missing");
        }
    };

    const handleSubmit = () => {
        if (userId) {
            dispatch(sendCoordinates({ x, y, r, userId }));
        } else {
            console.log('UserId is missing');
        }
    };

    return (
        <div className="main-page">
            <Header username={username} onLogout={handleLogout} />
            <div className="content">
                <div className="input-section">
                    <InputForm x={x} setX={setX} y={y} setY={setY} r={r} setR={setR} onSubmit={handleSubmit} />
                </div>
                <div className="canvas-section">
                    <CanvasArea r={r} results={results} onCanvasClick={handleCanvasClick} />
                </div>
            </div>
            <div className="results-table-container">
                {loading ? (
                    <p>Loading...</p>
                ) : (
                    <ResultsTable results={results} />
                )}
            </div>
        </div>
    );
};

export default MainPage;
