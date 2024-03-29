import { useQuery } from "@tanstack/react-query";
import { Article } from "../../validators/Article";
import ArticleCard from "../articleCard/ArticleCard";
import "./ArticlesList.css";
import axios from "axios";

export default function ArticlesList() {
  const { isPending, error, data } = useQuery({
    queryKey: ["articles"],
    queryFn: () =>
      fetch("http://localhost:8080/api/v1/articles").then((res) => res.json()),
  });

  if (isPending) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return (
    <div>
      <h1>Articles List</h1>
      <div>
        {data.map((article: Article) => (
          <ArticleCard
            id={article.id}
            content={article.content}
            imageLink={article.imageLink}
            key={`${article.id}-${crypto.randomUUID()}`}
          />
        ))}
      </div>
    </div>
  );
}
