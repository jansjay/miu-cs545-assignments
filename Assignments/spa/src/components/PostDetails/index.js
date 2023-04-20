import { useEffect, useState } from "react";
import Comments from "../Comments";
import { useNavigate, useParams } from "react-router-dom";
import { deletePost, getPost } from "../../api/Posts";

function PostDetails(props) {
  const [post, setPost] = useState({});
  const params = useParams();
  const navigate = useNavigate();
  useEffect(() => {
    getPost(params.id)
      .then((data) => setPost(data))
      .catch((error) => console.log(error));
  }, [params.id]);

  const onDelete = async (id) => {
    await deletePost(id);   
    navigate(`/posts?refresh=true`);
  };
  const onEdit = async (id) => {
    navigate(`/modify-post/${id}`);
  };

  if (post && post.id) {
    return (
      <div className="w3-card-4 w3-margin w3-white">
        <div className="w3-container">
          <h3>{post.title}</h3>
          <h5>{post.user.name}</h5>
          <div className="w3-container">
            <p>{post.content}</p>
          </div>
          <div className="w3-row">
            <div className="w3-col m8 s12">
              <p>
                <button onClick={() => onEdit(post.id)}>edit</button>
                <button onClick={() => onDelete(post.id)}>delete</button>
              </p>
            </div>
          </div>
          <Comments id={post.id}/>
        </div>
      </div>
    );
  }
  return "";
}

export default PostDetails;
