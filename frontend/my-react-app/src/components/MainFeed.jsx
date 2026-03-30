import { useState }      from "react";
import { useNavigate }   from "react-router-dom";
import { FeedHeader }    from "./feed/Header/FeedHeader.jsx";
import { LeftSidebar }   from "./feed/Sidebar/LeftSidebar";
import { FeedList }      from "./feed/FeedList";
import { RightPanel }    from "./feed/RightPanel/RightPanel";
import { Icon }          from "./Icon";
import { usePosts }      from "./hooks/usePosts.js";
 
// ─── MainFeed ─────────────────────────────────────────────────────────────────
// Page-level component — owns layout and sidebar state only.
// All content is delegated to focused child components.
// Props:
//   onLogin  — navigate to login page
//   onSignup — navigate to create account page
 
export default function MainFeed() {
  const navigate = useNavigate();
  const [sidebarOpen, setSidebarOpen] = useState(true);
  const { posts, toggleLike }         = usePosts();
 
  return (
    <div className="min-h-screen bg-[#0a0a0a] font-sans text-neutral-200">

      {/* Top bar */}
      <FeedHeader
        onToggleSidebar={() => setSidebarOpen((o) => !o)}
        onLogin={() => navigate("/login")}
        onSignup={() => navigate("/signup")}
        onHome={() => window.scrollTo({ top: 0, behavior: "smooth" })}
      />

      {/* Body */}
      <div className="flex w-full">

        {/* Left sidebar */}
        <LeftSidebar open={sidebarOpen} />

        {/* Center + Right */}
        <div className="mx-auto flex flex-1 max-w-[1280px]">

          {/* Feed */}
          <main className="min-w-0 flex-1 border-r border-white/5 px-6 py-6">
            <div className="mb-6 flex max-w-3xl items-center justify-between">
              <div>
                <h1 className="text-[21px] font-bold tracking-tight text-white">
                  Home Feed
                </h1>
                <p className="mt-1 text-[14px] text-neutral-700">
                  What's happening in the TV world right now.
                </p>
              </div>
              <button
                onClick={() => navigate("/signup")}
                className="flex shrink-0 items-center justify-center gap-2 rounded-lg bg-white px-3 py-2 text-[13px] font-semibold text-black transition-opacity hover:opacity-85"
              >
                <Icon name="plus" size={13} className="text-black" />
                New Post
              </button>
            </div>

            <div className="max-w-3xl">
              <FeedList posts={posts} onLike={toggleLike} />
            </div>
          </main>

          {/* Right sidebar */}
          <RightPanel />
        </div>
      </div>
    </div>
  );
}