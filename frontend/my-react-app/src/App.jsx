import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainFeed from "./components/MainFeed";
import Login from "./pages/Login";
import CreateAccount from "./pages/CreateAccount";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainFeed />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<CreateAccount />} />
      </Routes>
    </BrowserRouter>
  );
}
 