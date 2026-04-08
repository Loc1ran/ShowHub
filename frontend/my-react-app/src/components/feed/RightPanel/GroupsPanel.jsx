import { Icon } from "../../Icon";
import { GROUPS } from "../../../data/feedData";

// ─── GroupsPanel ──────────────────────────────────────────────────────────────
// Groups list panel for the right sidebar.

export function GroupsPanel() {
  return (
    <div className="rounded-2xl border border-white/6 bg-neutral-900 p-4">
      <div className="mb-4 flex items-center gap-2">
        <Icon name="users" size={13} className="text-neutral-500" />
        <h3 className="text-[13px] font-semibold text-neutral-400">Your Groups</h3>
      </div>

      <div className="flex flex-col gap-2">
        {GROUPS.map((group) => (
          <div
            key={group.name}
            className="cursor-pointer rounded-xl border border-white/6 bg-white/2 p-3 transition-colors hover:bg-white/4 hover:border-white/12"
          >
            <div className="mb-1 flex items-center justify-between">
              <span className="text-[13px] font-medium text-neutral-300">{group.name}</span>
              <span className="rounded-full bg-white/8 px-2 py-0.5 text-[11px] text-neutral-500">
                {group.count}
              </span>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
