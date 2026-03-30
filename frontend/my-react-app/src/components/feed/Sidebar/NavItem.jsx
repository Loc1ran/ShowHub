import { Icon } from "../../Icon";

// ─── NavItem ──────────────────────────────────────────────────────────────────
// A single left-sidebar navigation button.

export function NavItem({ label, icon, active, onClick }) {
  return (
    <button
      onClick={onClick}
      className={`flex w-full items-center gap-2.5 rounded-lg px-3 py-2 text-[13.5px] font-medium transition-colors
        ${active
          ? "bg-white/7 text-white"
          : "text-neutral-600 hover:bg-white/4 hover:text-neutral-300"
        }`}
    >
      <Icon name={icon} size={14} />
      {label}
    </button>
  );
}