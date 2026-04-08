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
      <div className="flex w-full relative" style={{ marginLeft: sidebarOpen ? '80px' : '0' }}>

        {/* Left sidebar */}
        <LeftSidebar open={sidebarOpen} />

        {/* Center + Right */}
        <div className="mx-auto flex flex-1 max-w-[1280px]">

          {/* Feed */}
          <main className="min-w-0 flex-1 px-6 py-0 relative">
            <div className="mb-4 pt-6">
              <div>
                <h1 className="text-[21px] font-bold tracking-tight text-white">
                  For You
                </h1>
              </div>
            </div>

            <div className="max-w-3xl pb-24 relative">
              <FeedList posts={posts} onLike={toggleLike} />
              
              {/* New Post Button - Pinned to bottom-right of feed */}
              <button
                onClick={() => navigate("/signup")}
                className="fixed bottom-8 flex h-14 w-14 items-center justify-center rounded-full bg-white text-black transition-opacity hover:opacity-85 shadow-lg"
                style={{ left: 'calc(50% + 300px)' }}
              >
                <Icon name="plus" size={24} className="text-black" />
              </button>
            </div>
          </main>

          {/* Right sidebar */}
          <RightPanel />
        </div>

      </div>
    </div>
  );
}