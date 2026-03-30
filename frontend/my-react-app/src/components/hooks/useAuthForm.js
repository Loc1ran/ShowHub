import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login, registerUser } from "../../services/client.js"

const LOGIN_INITIAL_FORM = { email: "", password: "" };
const SIGNUP_INITIAL_FORM = { name: "", username: "", email: "", password: "", confirmPassword: "" };

/**
 * @param {{ type: "login" | "signup", onSuccess: () => void }} options
 */
export function useAuthForm({ type = "login", onSuccess } = {}) {
  const navigate = useNavigate();
  const initialForm = type === "login" ? LOGIN_INITIAL_FORM : SIGNUP_INITIAL_FORM;
  
  const [form, setForm] = useState(initialForm);
  const [loading, setLoading] = useState(false);
  const [done, setDone] = useState(false);
  const [error, setError] = useState("");

  const updateField = (field) => (e) =>
    setForm((prev) => ({ ...prev, [field]: e.target.value }));

  const resetForm = () => {
    setForm(initialForm);
    setError("");
    setDone(false);
  };

  const validateEmail = (email) => {
    return email.includes("@") && email.includes(".");
  };

  const validate = () => {
    if (type === "login") {
      if (!form.email) return "Email is required";
      if (!form.password) return "Password is required";
      if (!validateEmail(form.email)) return "Enter a valid email address";
      if (form.password.length < 6) return "Password must be at least 6 characters";
    } else {
      if (!form.name?.trim()) return "Please enter your name";
      if (form.name.trim().length < 2) return "Name must be at least 2 characters";
      if (!form.username?.trim()) return "Please enter a username";
      if (form.username.includes(" ")) return "Username cannot contain spaces";
      if (form.username.length < 3) return "Username must be at least 3 characters";
      if (!form.email) return "Email is required";
      if (!validateEmail(form.email)) return "Enter a valid email address";
      if (!form.password) return "Password is required";
      if (form.password.length < 6) return "Password must be at least 6 characters";
      if (!form.confirmPassword) return "Please confirm your password";
      if (form.password !== form.confirmPassword) return "Passwords don't match";
    }
    return "";
  };

  const submit = async (e) => {
    e.preventDefault();
    const err = validate();
    if (err) {
      setError(err);
      return;
    }

    setError("");
    setLoading(true);

    try {
      let data;
      
      if (type === "login") {
        const payload = { email: form.email, password: form.password };
        data = await login(payload);
      } else {
        const payload = {
          name: form.name,
          username: form.username,
          email: form.email,
          password: form.password,
        };
        data = await registerUser(payload);
      }

      if (data?.accessToken) {
        localStorage.setItem("accessToken", data.accessToken);
        localStorage.setItem("authUser", JSON.stringify(data.user || { email: form.email }));
      }

      setDone(true);
      setTimeout(() => {
        onSuccess?.(data?.user);
        navigate("/");
      }, 900);
    } catch (err) {
      setError(err.message || "Authentication failed. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return {
    form,
    loading,
    done,
    error,
    updateField,
    submit,
    resetForm,
    setError,
  };
}
 