import { Card, CardContent, CardHeader } from "@/components/ui/card";
import DropZone from "../dropZone/DropZone";
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
    <Card className="card">
      <CardHeader>ID Article : {id}</CardHeader>
      <CardContent>
        Content : {content}; Image path is {imageLink || "no set"}
      </CardContent>
      <DropZone id={id}/>
    </Card>
  );
}
