import React, { useState } from "react";
import '../style/ErrorMessage.css';

const InputForm = ({ x, setX, y, setY, r, setR, onSubmit }) => {
    const [errorMessage, setErrorMessage] = useState("");

    const handleYChange = (value) => {
        setY(value);
        if (value === "") {
            setErrorMessage("Y coordinate is required.");
        } else if (isNaN(value) || value < -3 || value > 3) {
            setErrorMessage("Y must be a number between -3 and 3.");
        } else {
            setErrorMessage("");
        }
    };

    const handleSubmit = () => {
        if (y === "" || isNaN(y) || y < -3 || y > 3) {
            setErrorMessage("Please provide a valid Y coordinate.");
            return;
        }
        setErrorMessage("");
        onSubmit();
    };

    return (
        <div className="input-form">
            <label>
                X Coordinate:
                <select value={x} onChange={(e) => setX(e.target.value)}>
                    {["-2", "-1.5", "-1", "-0.5", "0", "0.5", "1", "1.5", "2"].map((value) => (
                        <option key={value} value={value}>
                            {value}
                        </option>
                    ))}
                </select>
            </label>
            <label>
                Y Coordinate:
                <input
                    type="text"
                    value={y}
                    onChange={(e) => handleYChange(e.target.value)}
                    placeholder="(-3 ... 3)"
                />
                {errorMessage && <p className="error-message">{errorMessage}</p>}
            </label>
            <label>
                Radius:
                <select value={r} onChange={(e) => setR(e.target.value)}>
                    {["0.5", "1", "1.5", "2"].map((value) => (
                        <option key={value} value={value}>
                            {value}
                        </option>
                    ))}
                </select>
            </label>
            <button
                className="submit-button"
                onClick={handleSubmit}
            >
                Submit
            </button>
        </div>
    );
};

export default InputForm;
