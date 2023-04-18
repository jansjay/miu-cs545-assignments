import { useEffect, useState } from "react";
import { deletePost, getPosts } from "../../api/Posts";
import PostDetails, {
  PostDetailsModification,
} from "../../components/PostDetails";
import Post from "../Post";

function Posts() {
  const [posts, setPosts] = useState([]);
  const [selectedPost, setSelectedPost] = useState({});
  const [refreshFlag, setRefreshFlag] = useState(false);
  const [formVisible, setFormVisible] = useState(false);
  useEffect(() => {
    getPosts()
      .then((data) => setPosts(data))
      .catch((error) => console.log(error));
  }, [refreshFlag]);
  const onDelete = async (id) => {
    await deletePost(id);
    setRefreshFlag(!refreshFlag);
    setSelectedPost({});
  };
  const onEdit = async (id) => {
    setFormVisible(true);
  };
  const onNew = async (id) => {
    setSelectedPost({});
    setFormVisible(true);
  };
  const onSelected = (id) => {
    setSelectedPost(posts.find((x) => x.id === id));    
  };
  const postDetailsModified = () => {
    setRefreshFlag(!refreshFlag);
    setSelectedPost({});
    setFormVisible(false);
  };
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
              onSelected={() => onSelected(post.id)}
            />
          ))}
        </ul>
      </div>
      <PostDetails id={selectedPost.id} onDelete={onDelete} onEdit={onEdit} />
      <div className="w3-col l4">
        <div className="w3-card w3-margin">
          <p>
            <button onClick={onNew}>new</button>
          </p>
        </div>
      </div>
      {formVisible ? (
        <PostDetailsModification
          id={selectedPost.id}
          postDetailsModified={postDetailsModified}
        />
      ) : (
        ""
      )}
    </>
  );
}

export default Posts;
