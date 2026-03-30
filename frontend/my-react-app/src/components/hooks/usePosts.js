import { useState } from "react";
import { POSTS } from "../../data/feedData";
 
// ─── usePosts ──────────────────────────────────────────────────────────────────
// Manages post state and like toggling.
// Keeps feed components free of business logic.
 
export function usePosts() {
  const [posts, setPosts] = useState(POSTS);
 
  const toggleLike = (id) => {
    setPosts((prev) =>
      prev.map((p) =>
        p.id === id
          ? { ...p, liked: !p.liked, likes: p.liked ? p.likes - 1 : p.likes + 1 }
          : p
      )
    );
  };
 
  return { posts, toggleLike };
}
 