import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';


export const sendCoordinates = createAsyncThunk(
    'results/sendCoordinates',
    async ({ x, y, r, userId }, { rejectWithValue }) => {
        try {
            const response = await axios.post('http://localhost:8080/laba4/api/controller/results', {
                x,
                y,
                r,
                userId,
            });
            return response.data;
        } catch (error) {
            return rejectWithValue(error.response?.data || 'Failed to send coordinates');
        }
    }
);


export const fetchUserResults = createAsyncThunk(
    'results/fetchUserResults',
    async (userId, { rejectWithValue }) => {
        try {
            const response = await axios.get(`http://localhost:8080/laba4/api/controller/results/${userId}`);
            return response.data;
        } catch (error) {
            return rejectWithValue(error.response?.data || 'Failed to fetch results');
        }
    }
);

const resultsSlice = createSlice({
    name: 'results',
    initialState: {
        results: [],
        loading: false,
        error: null,
    },
    reducers: {
        clearResults(state) {
            state.error=null;
            state.results = [];
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(sendCoordinates.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(sendCoordinates.fulfilled, (state, action) => {
                state.loading = false;
                state.results.push(action.payload);
            })
            .addCase(sendCoordinates.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            })


            .addCase(fetchUserResults.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchUserResults.fulfilled, (state, action) => {

                state.loading = false;
                state.results = action.payload;
            })
            .addCase(fetchUserResults.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            });
    },
});

export const { clearResults } = resultsSlice.actions;

export default resultsSlice.reducer;
