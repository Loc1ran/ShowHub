import { Icon } from "../../Icon";

// ─── PostImage ────────────────────────────────────────────────────────────────
// Optional image thumbnail with a play button overlay.

export function PostImage({ src }) {
  if (!src) return null;

  return (
    <div className="relative mb-3 cursor-pointer overflow-hidden rounded-lg">
      <img
        src={src}
        alt=""
        className="block h-56 w-full object-cover brightness-50 grayscale-[40%]"
      />
      {/* Play overlay */}
      <div className="absolute inset-0 flex items-center justify-center">
        <div className="flex h-10 w-10 items-center justify-center rounded-full border border-white/15 bg-white/10 text-white backdrop-blur-sm">
          <Icon name="play" size={14} fill="white" className="text-white" />
        </div>
      </div>
    </div>
  );
}