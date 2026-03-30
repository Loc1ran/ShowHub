import { Icon } from "../../Icon";
import { TAGS }  from "../../../data/feedData";

// ─── TagsPanel ────────────────────────────────────────────────────────────────
// Popular hashtag pills.

export function TagsPanel() {
  return (
    <div className="rounded-xl border border-white/6 bg-neutral-900 p-4">
      <div className="mb-3 flex items-center gap-2">
        <Icon name="hash" size={13} className="text-neutral-500" />
        <h3 className="text-[13px] font-semibold text-neutral-400">Popular Tags</h3>
      </div>

      <div className="flex flex-wrap gap-1.5">
        {TAGS.map((tag) => (
          <span
            key={tag}
            className="cursor-pointer rounded-full border border-white/8 px-3 py-1 text-[12px] text-neutral-600 transition-colors hover:border-white/20 hover:text-neutral-300"
          >
            {tag}
          </span>
        ))}
      </div>
    </div>
  );
}