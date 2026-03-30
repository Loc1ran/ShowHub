import { useState } from "react";
import { NavItem }   from "./NavItem";
import { GroupItem } from "./GroupItem";
import { Icon }      from "../../Icon";
import { NAV_ITEMS, GROUPS } from "../../../data/feedData";

// ─── LeftSidebar ──────────────────────────────────────────────────────────────
// Collapsible left sidebar with nav links, groups, and a "New Post" CTA.
// Props:
//   open     — controlled open state from parent
//   onSignup — navigates to create account (New Post requires auth)

export function LeftSidebar({ open, onSignup }) {
  const [activeNav, setActiveNav] = useState("Home");

  return (
    <aside
      className={`shrink-0 overflow-hidden border-r border-white/5 transition-all duration-200 ${
        open ? "w-[200px]" : "w-0"
      }`}
    >
      <div className="flex h-[calc(100vh-56px)] w-[200px] flex-col overflow-y-auto px-3 py-4 sticky top-14">

        {/* Navigation */}
        <nav className="mb-5 flex flex-col gap-1">
          {NAV_ITEMS.map((item) => (
            <NavItem
              key={item.label}
              label={item.label}
              icon={item.icon}
              active={activeNav === item.label}
              onClick={() => setActiveNav(item.label)}
            />
          ))}
        </nav>

        {/* Groups */}
        <div className="mb-auto">
          <p className="mb-2 px-3 text-[11px] font-semibold uppercase tracking-widest text-neutral-800">
            Groups
          </p>
          <div className="flex flex-col gap-1">
            {GROUPS.map((g) => (
              <GroupItem key={g.name} {...g} />
            ))}
          </div>
        </div>
      </div>
    </aside>
  );
}