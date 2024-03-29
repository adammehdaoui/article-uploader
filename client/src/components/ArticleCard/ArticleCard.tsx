import DropZone from "../DropZone/DropZone";
import "./ArticleCard.css";

export default function ArticleCard({
  id,
  content,
  imageLink,
}: {
  id: string;
  content: string;
  imageLink: string;
}) {
  return (
    <div className="card">
      <h1>{id}</h1>
      <p>{content}</p>
      <p>Image path is {imageLink || "no set"}</p>
      <DropZone />
    </div>
  );
}
