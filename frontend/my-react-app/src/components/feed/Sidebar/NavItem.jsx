import { Icon } from "../../Icon";

// ─── NavItem ──────────────────────────────────────────────────────────────────
// A single left-sidebar navigation button with icon-only design and hover tooltip.

export function NavItem({ label, icon, active, onClick }) {
  return (
    <div className="group relative flex items-center justify-center">
      <button
        onClick={onClick}
        className={`flex h-16 w-16 items-center justify-center rounded-2xl transition-colors
          ${active
            ? "bg-white/10 text-white"
            : "text-neutral-600 hover:bg-white/8 hover:text-neutral-300"
          }`}
        title={label}
      >
        <Icon name={icon} size={32} />
      </button>
      <div className="pointer-events-none absolute left-full ml-4 whitespace-nowrap rounded-lg bg-white/90 px-3 py-2 text-[12px] font-medium text-black shadow-lg opacity-0 transition-opacity group-hover:opacity-100">
        {label}
      </div>
    </div>
  );
}