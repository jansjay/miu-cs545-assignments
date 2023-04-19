import { useEffect, useState } from "react";
import { deletePost, getPosts } from "../../api/Posts";
import PostDetails, {
  PostDetailsModification,
} from "../../components/PostDetails";
import Post from "../Post";
import { GlobalContext } from "../../context/GlobalContext";

function Posts() {
  const [posts, setPosts] = useState([]);
  const [refreshFlag, setRefreshFlag] = useState(false);
  const [formVisible, setFormVisible] = useState(false);  
  const [globalContext, setGlobalContext] = useState({});
  useEffect(() => {
    getPosts()
      .then((data) => setPosts(data))
      .catch((error) => console.log(error));
  }, [refreshFlag]);
  const onDelete = async (id) => {
    await deletePost(id);
    setRefreshFlag(!refreshFlag);
    setGlobalContext({...globalContext, selectedPost: {}});
  };
  const onEdit = async (id) => {
    setFormVisible(true);
  };
  const onNew = async (id) => {
    setGlobalContext({...globalContext, selectedPost: {}});
    setFormVisible(true);
  };
  const onSelected = (id) => {
    let c = {...globalContext, selectedPost: posts.find((x) => x.id === id)};
    setGlobalContext(c);    
  };
  const postDetailsModified = () => {
    setRefreshFlag(!refreshFlag);
    setGlobalContext({...globalContext, selectedPost: {}});
    setFormVisible(false);
  };
  return (
    <>
    <GlobalContext.Provider value={globalContext}>    
    
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
      <PostDetails onDelete={onDelete} onEdit={onEdit} />
      <div className="w3-col l4">
        <div className="w3-card w3-margin">
          <p>
            <button onClick={onNew}>new</button>
          </p>
        </div>
      </div>
      {formVisible ? (
        <PostDetailsModification
          postDetailsModified={postDetailsModified}
        />
      ) : (
        ""
      )}
      </GlobalContext.Provider>
    </>
  );
}

export default Posts;
