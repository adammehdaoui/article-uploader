import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import { Article } from "../../validators/Article";
import ArticleCard from "../articleCard/ArticleCard";
import "./ArticlesList.css";

export default function ArticlesList() {
  const { isPending, error, data } = useQuery({
    queryKey: ["articles"],
    queryFn: () =>
      axios
        .get("http://localhost:8080/api/v1/articles")
        .then((res) => res.data),
  });

  if (isPending) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  data.sort((a: Article, b: Article) => {
    if (a.id < b.id) return -1;
    if (a.id > b.id) return 1;
    return 0;
  });

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
