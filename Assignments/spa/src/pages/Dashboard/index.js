import { useState } from "react";
import PostsCollection from "../../api/Posts";
import Posts from "../../components/Posts";
import PostDetails from "../../components/PostDetails";

function Dashboard() {
  const [posts, setPosts] = useState(PostsCollection);
  const [selectedPost, setSelectedPost] = useState({});
  const [newTitle, setNewTitle] = useState("");
  const onDelete = () => {
    console.log("onDelete");
  };
  const onEdit = () => {
    console.log("onEdit");
  };
  const onSelected = (id) => {
    setSelectedPost(posts.find((x) => x.id === id));
    setNewTitle("");
  };
  const updateInputTitle = (evt) => {
    setNewTitle(evt.target.value);
  }
  const changeSelectedTitle = () => {
    if (selectedPost && selectedPost.id) {
      const newPosts = posts.map((p) =>
        p.id === selectedPost.id ? { ...p, title: newTitle } : p
      );
      setPosts(newPosts);
    }
  };
  return (
    <>
      <div>
        <Posts
          posts={posts}
          onDelete={onDelete}
          onEdit={onEdit}
          onSelected={onSelected}
        />
      </div>
      {selectedPost.id ? (
        <div className="w3-container">
          <input value={newTitle} onChange={evt => updateInputTitle(evt)} name="name"></input>
          <button onClick={changeSelectedTitle}>Change Title</button>
        </div>
      ) : (
        ""
      )}
      <PostDetails post={selectedPost} onDelete={onDelete} onEdit={onEdit} />
    </>
  );
}
export default Dashboard;
