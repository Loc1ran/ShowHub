import { TrendingPanel } from "./TrendingPanel";
import { TagsPanel }     from "./TagsPanel";

// ─── RightPanel ───────────────────────────────────────────────────────────────
// Right sidebar — sticky, scrollable, composed of panel widgets.
// Add new panels here without touching the feed layout.

export function RightPanel() {
  return (
    <aside className="sticky top-14 hidden h-[calc(100vh-56px)] w-[268px] shrink-0 flex-col gap-3 overflow-y-auto px-4 py-5 xl:flex">
      <TrendingPanel />
      <TagsPanel />
    </aside>
  );
}