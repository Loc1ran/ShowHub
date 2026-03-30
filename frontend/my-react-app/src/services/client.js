import axios from "axios"

export const login = async (usernameAndPassword) => {
    try{
        return await axios.post(`${import.meta.env.VITE_API_BASE_URL}/api/v1/auth/login`, usernameAndPassword);
    } catch (err){
        throw err;
    }
}

export const registerUser = async (usernameAndPassword) => {
    try{
        return await axios.post(`${import.meta.env.VITE_API_BASE_URL}/api/v1/auth/register`, usernameAndPassword)
    } catch (err) {
        throw err;
    }
}