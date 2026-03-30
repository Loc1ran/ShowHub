import { Icon } from "../../Icon";

// ─── PostActions ──────────────────────────────────────────────────────────────
// Like, comment, and share action row at the bottom of each post.

export function PostActions({ liked, likes, comments, onLike }) {
  return (
    <div className="flex items-center gap-1 border-t border-white/5 pt-3 mt-1">

      {/* Like */}
      <button
        onClick={onLike}
        className={`flex items-center gap-1.5 rounded-md px-3 py-1.5 text-[13px] font-medium transition-colors
          ${liked
            ? "text-neutral-300 hover:bg-white/4"
            : "text-neutral-700 hover:bg-white/4 hover:text-neutral-500"
          }`}
      >
        <Icon
          name="heart"
          size={13}
          fill={liked ? "currentColor" : "none"}
        />
        {likes}
      </button>

      {/* Comment */}
      <button className="flex items-center gap-1.5 rounded-md px-3 py-1.5 text-[13px] font-medium text-neutral-700 transition-colors hover:bg-white/4 hover:text-neutral-500">
        <Icon name="comment" size={13} />
        {comments}
      </button>

      {/* Share */}
      <button className="ml-auto flex items-center gap-1.5 rounded-md px-3 py-1.5 text-[13px] font-medium text-neutral-700 transition-colors hover:bg-white/4 hover:text-neutral-500">
        <Icon name="share" size={13} />
        Share
      </button>
    </div>
  );
}