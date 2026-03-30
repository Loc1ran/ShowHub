import { Icon }        from "../../Icon";
import { PostImage }   from "./PostImage";
import { PostActions } from "./PostActions";

// ─── PostCard ─────────────────────────────────────────────────────────────────
// A single feed post — avatar, header, body, optional image, and actions.
// Props mirror the post shape from feedData.js.

export function PostCard({ post, onLike }) {
  const { initials, username, time, show, content, image, liked, likes, comments } = post;

  return (
    <article className="rounded-xl border border-white/6 bg-neutral-900 p-4 transition-colors hover:border-white/10">

      {/* Header */}
      <div className="mb-3 flex items-center gap-3">
        {/* Avatar */}
        <div className="flex h-9 w-9 shrink-0 items-center justify-center rounded-full border border-white/10 bg-neutral-800 text-[11px] font-semibold tracking-wide text-neutral-500">
          {initials}
        </div>

        {/* Meta */}
        <div className="flex-1 min-w-0">
          <div className="flex items-center gap-2">
            <span className="text-[13px] font-semibold text-neutral-300">{username}</span>
            <span className="text-[12px] text-neutral-800">· {time}</span>
          </div>
          <span className="text-[12px] text-neutral-600">{show}</span>
        </div>

        {/* More */}
        <button className="flex rounded p-1 text-neutral-800 transition-colors hover:text-neutral-500">
          <Icon name="more" size={15} />
        </button>
      </div>

      {/* Body */}
      <p className="mb-3 text-[14px] leading-relaxed text-neutral-500">
        {content}
      </p>

      {/* Image */}
      <PostImage src={image} />

      {/* Actions */}
      <PostActions
        liked={liked}
        likes={likes}
        comments={comments}
        onLike={onLike}
      />
    </article>
  );
}