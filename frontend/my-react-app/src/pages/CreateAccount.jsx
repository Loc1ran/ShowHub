import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuthForm } from "../components/hooks/useAuthForm";

export default function CreateAccount() {
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);
  const { form, loading, error, updateField, submit } = useAuthForm({ type: "signup" });

  const handleLogoClick = () => {
    setIsLoading(true);
    setTimeout(() => {
      navigate("/");
      setIsLoading(false);
    }, 500);
  };

  const handleSubmit = (e) => {
    submit(e);
  };

  return (
    <div className="min-h-screen bg-[#0a0a0a] font-sans text-neutral-200">
      {/* Top Left Logo */}
      <button
        onClick={handleLogoClick}
        disabled={isLoading}
        className="fixed top-6 left-6 flex flex-col items-start hover:opacity-80 transition-opacity disabled:opacity-60"
      >
        <span className="text-2xl font-bold text-white">ShowHub</span>
        <div className="w-16 h-1 bg-white rounded-full mt-1 overflow-hidden">
          <div
            className={`h-full bg-white transition-all duration-500 ${
              isLoading ? "w-full" : "w-0"
            }`}
          />
        </div>
      </button>

      <div className="flex items-center justify-center min-h-screen px-4 py-8">
        <div className="w-full max-w-md">
          {/* Header */}
          <div className="mb-8 text-center">
            <p className="text-neutral-400 text-lg">Join the conversation</p>
          </div>

          {/* Form Card */}
          <div className="rounded-2xl border border-white/10 bg-neutral-900/50 backdrop-blur-sm p-8 shadow-2xl">
          {/* Error Message */}
          {error && (
            <div className="rounded-xl bg-red-500/10 border border-red-500/30 p-4 text-sm text-red-400 mb-6">
              {error}
            </div>
          )}

          {/* Form */}
          <form onSubmit={handleSubmit} className="space-y-5">
            <div>
              <label className="block text-sm font-semibold text-neutral-300 mb-3">
                Full Name
              </label>
              <input
                type="text"
                name="name"
                value={form.name}
                onChange={updateField("name")}
                className="w-full rounded-lg border border-white/15 bg-white/8 px-4 py-3 text-white placeholder-neutral-500 transition-all hover:bg-white/10 hover:border-white/25 focus:bg-white/12 focus:border-white/40 focus:outline-none"
                placeholder="John Doe"
              />
            </div>

            <div>
              <label className="block text-sm font-semibold text-neutral-300 mb-3">
                Username
              </label>
              <input
                type="text"
                name="username"
                value={form.username}
                onChange={updateField("username")}
                className="w-full rounded-lg border border-white/15 bg-white/8 px-4 py-3 text-white placeholder-neutral-500 transition-all hover:bg-white/10 hover:border-white/25 focus:bg-white/12 focus:border-white/40 focus:outline-none"
                placeholder="yourname"
              />
            </div>

            <div>
              <label className="block text-sm font-semibold text-neutral-300 mb-3">
                Email
              </label>
              <input
                type="email"
                name="email"
                value={form.email}
                onChange={updateField("email")}
                className="w-full rounded-lg border border-white/15 bg-white/8 px-4 py-3 text-white placeholder-neutral-500 transition-all hover:bg-white/10 hover:border-white/25 focus:bg-white/12 focus:border-white/40 focus:outline-none"
                placeholder="your@email.com"
              />
            </div>

            <div>
              <label className="block text-sm font-semibold text-neutral-300 mb-3">
                Password
              </label>
              <input
                type="password"
                name="password"
                value={form.password}
                onChange={updateField("password")}
                className="w-full rounded-lg border border-white/15 bg-white/8 px-4 py-3 text-white placeholder-neutral-500 transition-all hover:bg-white/10 hover:border-white/25 focus:bg-white/12 focus:border-white/40 focus:outline-none"
                placeholder="••••••••"
              />
            </div>

            <div>
              <label className="block text-sm font-semibold text-neutral-300 mb-3">
                Confirm Password
              </label>
              <input
                type="password"
                name="confirmPassword"
                value={form.confirmPassword}
                onChange={updateField("confirmPassword")}
                className="w-full rounded-lg border border-white/15 bg-white/8 px-4 py-3 text-white placeholder-neutral-500 transition-all hover:bg-white/10 hover:border-white/25 focus:bg-white/12 focus:border-white/40 focus:outline-none"
                placeholder="••••••••"
              />
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full rounded-lg bg-white py-3 text-sm font-semibold text-black transition-all hover:opacity-90 active:scale-95 disabled:opacity-60"
            >
              {loading ? "Creating account..." : "Create account"}
            </button>
          </form>
        </div>

        {/* Footer */}
        <div className="mt-8 text-center text-sm text-neutral-400">
          Already have an account?{" "}
          <button
            onClick={() => navigate("/login")}
            className="text-white font-semibold hover:text-neutral-200 transition-colors"
          >
            Log in
          </button>
        </div>
      </div>
    </div>
    </div>
  );
}
