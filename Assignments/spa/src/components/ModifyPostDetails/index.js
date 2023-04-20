import { useEffect, useRef, useState } from "react";
import { addNewPost, getPost, updatePost } from "../../api/Posts";
import { useNavigate, useParams } from "react-router-dom";

export function ModifyPostDetails(props) {
  const htmlFormElements = useRef();
  const [post, setPost] = useState({});
  const params = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (params.id) {
      getPost(params.id)
        .then((data) => setPost(data))
        .catch((error) => console.log(error));
    }
  }, [params.id]);

  const okClicked = async (evt) => {
    evt.preventDefault();
    let modifiedPost = { ...post };
    modifiedPost = {
      ...modifiedPost,
      title: htmlFormElements.current["title"].value,
    };
    modifiedPost = {
      ...modifiedPost,
      content: htmlFormElements.current["content"].value,
    };
    if (post.id) {
      await updatePost(modifiedPost);
    } else {
      await addNewPost(modifiedPost);
    }
    navigate(`/posts?refresh=true`);
  };
  return (
    <div className="w3-card-4 w3-margin w3-white">
      <div className="w3-container">
        <form ref={htmlFormElements}>
          <label htmlFor="title">Title</label>
          <br />
          <input defaultValue={post ? post.title : ""} name="title" />
          <br />
          <label htmlFor="content">Content</label>
          <br />
          <textarea defaultValue={post ? post.content : ""} name="content" />
          <br />
          <button onClick={okClicked}>Ok</button>
        </form>
      </div>
    </div>
  );
}
