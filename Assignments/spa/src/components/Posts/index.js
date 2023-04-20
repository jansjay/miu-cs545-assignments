import { useEffect, useState } from "react";
import Post from "../Post";
import { useParams } from "react-router-dom";
import { getPosts } from "../../api/Posts";

function Posts() {
  const [posts, setPosts] = useState([]);
  const params = useParams();
  useEffect(() => {
    getPosts()
      .then((data) => setPosts(data))
      .catch((error) => console.log(error));
  }, [params]);

  return (
    <>
      <div className="w3-col l4">
        <div className="w3-card w3-margin"></div>
      </div>
      <div className="w3-card w3-margin">
        <div className="w3-container w3-padding">
          <h4>Posts</h4>
        </div>
        <ul className="w3-ul w3-hoverable w3-white">
          {posts.map((post) => (
            <Post
              key={post.id}
              post={post}
            />
          ))}
        </ul>
      </div>
    </>
  );
}

export default Posts;
