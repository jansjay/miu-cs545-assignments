import { useEffect, useState } from "react";
import { addNewPost, getPost, updatePost } from "../../api/Posts";
import Comments from "../Comments";

function PostDetails(props) {
  const [post, setPost] = useState({});
  useEffect(() => {
    if (props.id) {
      getPost(props.id)
        .then((data) => setPost(data))
        .catch((error) => console.log(error));
    } else {
      setPost({});
    }
  }, [props.id]);

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
                <button onClick={() => props.onEdit(post.id)}>edit</button>
                <button onClick={() => props.onDelete(post.id)}>delete</button>
              </p>
            </div>
          </div>
          <Comments id={post.id} />
        </div>
      </div>
    );
  }
  return "";
}

export function PostDetailsModification(props) {
  const [post, setPost] = useState({});
  useEffect(() => {
    if (props.id) {
      getPost(props.id)
        .then((data) => setPost(data))
        .catch((error) => console.log(error));
    } else {
      setPost({});
    }
  }, [props.id]);
  const updateTitle = (evt) => {
    setPost({ ...post, title: evt.target.value });
  };
  const updateContent = (evt) => {
    setPost({ ...post, content: evt.target.value });
  };
  const okClicked = async () => {
    if (post.id) {
      await updatePost(post);
    } else {
      await addNewPost(post);
    }
    props.postDetailsModified();
  };
  return (
    <div className="w3-card-4 w3-margin w3-white">
      <div className="w3-container">
        {" "}
        <label for="title">Title</label><br/>
        <input
          value={post.title}
          onChange={(evt) => updateTitle(evt)}
          name="title"
        /><br/>
        <label for="content">Content</label><br/>
        <textarea
          value={post.content}
          onChange={(evt) => updateContent(evt)}
          name="content"
        /><br/>
        <button onClick={okClicked}>Ok</button>
      </div>
    </div>
  );
}
export default PostDetails;
