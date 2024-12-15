import React, { useRef, useEffect } from "react";

const CanvasArea = ({ r, results, onCanvasClick }) => {
    const canvasRef = useRef(null);

    useEffect(() => {
        drawCanvas();
    }, [r, results]);

    const drawCanvas = () => {
        const canvas = canvasRef.current;
        const ctx = canvas.getContext("2d");
        const canvasSize = 350;
        const center = canvasSize / 2;
        const scale = 100;

        ctx.clearRect(0, 0, canvasSize, canvasSize);

        ctx.fillStyle = "rgba(0, 100, 255, 0.5)";

        ctx.beginPath();
        ctx.arc(center, center, scale / 2, Math.PI / 2, Math.PI);
        ctx.lineTo(center, center);
        ctx.closePath();
        ctx.fill();

        ctx.fillRect(center, center - scale, scale, scale);

        ctx.beginPath();
        ctx.moveTo(center, center);
        ctx.lineTo(center - scale / 2, center);
        ctx.lineTo(center, center - scale);
        ctx.closePath();
        ctx.fill();

        ctx.strokeStyle = "black";
        ctx.lineWidth = 1;
        ctx.beginPath();
        ctx.moveTo(center, 0);
        ctx.lineTo(center, canvasSize);
        ctx.moveTo(0, center);
        ctx.lineTo(canvasSize, center);
        ctx.stroke();

        ctx.fillStyle = "black";
        ctx.font = "12px Arial";
        ctx.textAlign = "center";
        ctx.textBaseline = "middle";

        ctx.fillText("0", center, center + 12);
        ctx.fillText(`${r}`, center + scale, center + 12);
        ctx.fillText(`${r / 2}`, center + scale / 2, center + 12);
        ctx.fillText(`-${r / 2}`, center - scale / 2, center + 12);
        ctx.fillText(`-${r}`, center - scale, center + 12);
        ctx.fillText(`${r}`, center - 12, center - scale);
        ctx.fillText(`${r / 2}`, center - 12, center - scale / 2);
        ctx.fillText(`-${r / 2}`, center - 12, center + scale / 2);
        ctx.fillText(`-${r}`, center - 12, center + scale);

        results.forEach((result) => {
            const pointX = center + (result.x / r) * scale;
            const pointY = center - (result.y / r) * scale;
            ctx.fillStyle = result.hit ? "green" : "red";
            ctx.beginPath();
            ctx.arc(pointX, pointY, 4, 0, 2 * Math.PI);
            ctx.fill();
        });
    };

    return (
        <canvas
            ref={canvasRef}
            width="350"
            height="350"
            className="canvas"
            onClick={(e) => {
                const canvas = canvasRef.current;
                const rect = canvas.getBoundingClientRect();
                const xClick = (((e.clientX - rect.left - canvas.width / 2) / 100) * r).toFixed(2);
                const yClick = (((canvas.height / 2 - (e.clientY - rect.top)) / 100) * r).toFixed(2);
                onCanvasClick({ x: xClick, y: yClick });
            }}
        />
    );
};

export default CanvasArea;
