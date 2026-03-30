// ─── GroupItem ────────────────────────────────────────────────────────────────
// A single group row in the left sidebar.

export function GroupItem({ name, count, active }) {
  return (
    <div
      className={`flex cursor-pointer items-center justify-between rounded-lg px-2.5 py-1.5 transition-colors
        ${active ? "bg-white/6" : "hover:bg-white/3"}`}
    >
      <span
        className={`text-[13px] ${active ? "font-medium text-neutral-300" : "font-normal text-neutral-600"}`}
      >
        {name}
      </span>
      <span className="rounded-full bg-white/4 px-2 py-0.5 text-[11px] text-neutral-700">
        {count}
      </span>
    </div>
  );
}