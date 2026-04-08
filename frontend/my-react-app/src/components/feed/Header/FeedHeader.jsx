import { Icon } from "../../Icon";

// ─── FeedHeader ───────────────────────────────────────────────────────────────
// Sticky top navigation bar.
// Props:
//   onToggleSidebar — opens/closes the left sidebar
//   onLogin         — navigates to login page
//   onSignup        — navigates to create account page

export function FeedHeader({ onToggleSidebar, onLogin, onSignup, onHome }) {
  return (
    <header className="sticky top-0 z-50 flex h-14 items-center justify-between gap-4 border-b border-white/5 bg-[#0a0a0a]/95 px-4 backdrop-blur-sm">

      {/* Left — sidebar toggle + wordmark */}
      <div className="flex min-w-[180px] items-center gap-3">
        <button
          onClick={onToggleSidebar}
          className="flex h-8 w-8 items-center justify-center rounded-lg border border-white/8 text-neutral-600 transition-colors hover:border-white/15 hover:text-neutral-300"
        >
          <Icon name="menu" size={14} />
        </button>
        <button
          onClick={onHome}
          className="flex flex-col items-start hover:opacity-80 transition-opacity"
        >
          <span className="text-[15px] font-bold tracking-tight text-white">ShowHub</span>
          <div className="w-12 h-0.5 bg-white rounded-full mt-0.5"></div>
        </button>
      </div>

      {/* Center — search */}
      <div className="flex max-w-md flex-1 items-center gap-2 rounded-lg border border-white/8 bg-white/4 px-3 py-2 text-neutral-600">
        <Icon name="search" size={13} />
        <span className="text-[13px]">Search shows, people, discussions…</span>
      </div>

      {/* Right — auth */}
      <div className="flex min-w-[180px] items-center justify-end gap-2">
        <button
          onClick={onLogin}
          className="rounded-lg border border-white/10 px-4 py-1.5 text-[13px] font-medium text-neutral-500 transition-colors hover:border-white/25 hover:text-neutral-200"
        >
          Log in
        </button>
        <button
          onClick={onSignup}
          className="rounded-lg bg-white px-4 py-1.5 text-[13px] font-semibold text-black transition-opacity hover:opacity-85"
        >
          Create account
        </button>
      </div>
    </header>
  );
}