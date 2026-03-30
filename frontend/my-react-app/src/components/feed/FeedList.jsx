import { PostCard } from "./PostCard/PostCard";

// ─── FeedList ─────────────────────────────────────────────────────────────────
// Renders the scrollable list of posts.
// Props:
//   posts      — array of post objects
//   onLike(id) — called when a post's like button is clicked

export function FeedList({ posts, onLike }) {
  return (
    <div className="flex flex-col gap-3">
      {posts.map((post) => (
        <PostCard
          key={post.id}
          post={post}
          onLike={() => onLike(post.id)}
        />
      ))}
    </div>
  );
}