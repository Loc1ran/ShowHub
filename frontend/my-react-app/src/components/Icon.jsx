// ─── Icon.jsx ─────────────────────────────────────────────────────────────────
// Single source for all SVG icons used across the feed.
// Add new icons here — never inline SVG in other components.

const PATHS = {
  home:     "M3 12L12 3l9 9M5 10v9h5v-5h4v5h5v-9",
  shows:    "M15 10l4.553-2.277A1 1 0 0121 8.645v6.71a1 1 0 01-1.447.894L15 14M3 8a2 2 0 012-2h10a2 2 0 012 2v8a2 2 0 01-2 2H5a2 2 0 01-2-2V8z",
  discuss:  "M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z",
  trending: "M13 7h8m0 0v8m0-8l-8 8-4-4-6 6",
  tags:     "M7 7h.01M7 3h5a1.99 1.99 0 011.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A2 2 0 013 12V7a4 4 0 014-4z",
  heart:    "M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z",
  comment:  "M17 8h2a2 2 0 012 2v6a2 2 0 01-2 2h-2v4l-4-4H9a1.994 1.994 0 01-1.414-.586m0 0L11 14h4a2 2 0 002-2V6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2v4l.586-.586z",
  share:    "M8.684 13.342C8.886 12.938 9 12.482 9 12c0-.482-.114-.938-.316-1.342m0 2.684a3 3 0 110-2.684m0 2.684l6.632 3.316m-6.632-6l6.632-3.316m0 0a3 3 0 105.367-2.684 3 3 0 00-5.367 2.684zm0 9.316a3 3 0 105.368 2.684 3 3 0 00-5.368-2.684z",
  menu:     "M4 6h16M4 12h16M4 18h16",
  search:   "M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z",
  more:     "M5 12h.01M12 12h.01M19 12h.01",
  plus:     "M12 4v16m8-8H4",
  fire:     "M17.657 18.657A8 8 0 016.343 7.343S7 9 9 10c0-2 .5-5 2.986-7C14 5 16.09 5.777 17.656 7.343A7.975 7.975 0 0120 13a7.975 7.975 0 01-2.343 5.657z",
  hash:     "M7 20l4-16m2 16l4-16M6 9h14M4 15h14",
  play:     "M5 3l14 9-14 9V3z",
  users:    "M17 21v-2a4 4 0 00-4-4H5a4 4 0 00-4 4v2M16 11a3 3 0 11-6 0 3 3 0 016 0zM9 7a2 2 0 11-4 0 2 2 0 014 0z",
};

/**
 * @param {{ name: string, size?: number, className?: string, fill?: string }} props
 */
export function Icon({ name, size = 16, className = "", fill = "none" }) {
  return (
    <svg
      width={size}
      height={size}
      viewBox="0 0 24 24"
      fill={fill}
      stroke="currentColor"
      strokeWidth="1.5"
      strokeLinecap="round"
      strokeLinejoin="round"
      className={`shrink-0 ${className}`}
    >
      <path d={PATHS[name]} />
    </svg>
  );
}