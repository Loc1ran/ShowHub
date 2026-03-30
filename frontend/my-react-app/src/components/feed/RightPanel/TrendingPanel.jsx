import { Icon }     from "../../Icon";
import { TRENDING } from "../../../data/feedData";

// ─── TrendingPanel ────────────────────────────────────────────────────────────
// Displays the ranked list of trending shows.

export function TrendingPanel() {
  return (
    <div className="rounded-xl border border-white/6 bg-neutral-900 p-4">
      <div className="mb-3 flex items-center gap-2">
        <Icon name="fire" size={13} className="text-neutral-500" />
        <h3 className="text-[13px] font-semibold text-neutral-400">Trending Shows</h3>
      </div>

      <div className="flex flex-col">
        {TRENDING.map((item) => (
          <div
            key={item.rank}
            className="flex cursor-pointer items-center gap-3 border-b border-white/4 py-2 transition-opacity last:border-none hover:opacity-70"
          >
            <span className="w-3.5 shrink-0 text-[11px] font-semibold text-neutral-800">
              {item.rank}
            </span>
            <span className="flex-1 text-[13px] font-medium text-neutral-500">
              {item.title}
            </span>
            <span className="text-[11px] text-neutral-700">
              {item.posts} posts
            </span>
          </div>
        ))}
      </div>
    </div>
  );
}