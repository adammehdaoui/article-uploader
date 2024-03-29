import ArticleList from "../../components/articlesList/ArticlesList";
import "./Home.css";

export default function Home() {
  return (
    <div className="list-container">
      <h1>Welcome home!</h1>
      <ArticleList />
    </div>
  );
}
