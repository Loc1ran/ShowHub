import { useState } from "react";
import { NavItem }   from "./NavItem";
import { NAV_ITEMS } from "../../../data/feedData";

// ─── LeftSidebar ──────────────────────────────────────────────────────────────
// Compact icon-only left sidebar with navigation links.
// Props:
//   open     — controlled open state from parent
//   onSignup — navigates to create account (New Post requires auth)

export function LeftSidebar({ open, onSignup }) {
  const [activeNav, setActiveNav] = useState("Home");

  return (
    <aside
      className={`fixed left-0 top-0 z-40 overflow-hidden transition-all duration-200 ${
        open ? "w-[80px]" : "w-0"
      }`}
    >
      <div 
        className="flex h-screen w-[80px] flex-col items-center justify-center overflow-hidden"
        style={{
          scrollbarWidth: 'none',
          msOverflowStyle: 'none',
        }}
      >
        {/* Navigation */}
        <nav className="flex flex-col items-center justify-center gap-3">
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
      </div>
    </aside>
  );
}