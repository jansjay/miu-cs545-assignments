import { useContext, useRef, } from "react";
import { addNewPost, updatePost } from "../../api/Posts";
import Comments from "../Comments";
import { GlobalContext } from "../../context/GlobalContext";

function PostDetails(props) {
  const globalContext = useContext(GlobalContext);  
  if (globalContext.selectedPost && globalContext.selectedPost.id) {
    return (
      <div className="w3-card-4 w3-margin w3-white">
        <div className="w3-container">
          <h3>{globalContext.selectedPost.title}</h3>
          <h5>{globalContext.selectedPost.user.name}</h5>
          <div className="w3-container">
            <p>{globalContext.selectedPost.content}</p>
          </div>
          <div className="w3-row">
            <div className="w3-col m8 s12">
              <p>
                <button onClick={() => props.onEdit(globalContext.selectedPost.id)}>edit</button>
                <button onClick={() => props.onDelete(globalContext.selectedPost.id)}>delete</button>
              </p>
            </div>
          </div>
          <Comments />
        </div>
      </div>
    );
  }
  return "";
}

export function PostDetailsModification(props) {
  const globalContext = useContext(GlobalContext);  
  const htmlFormElements = useRef();
  
  const okClicked = async (evt) => {
    evt.preventDefault();
    let post = globalContext.selectedPost;
    post.title = htmlFormElements.current['title'].value;
    post.content = htmlFormElements.current['content'].value;
    if (globalContext.selectedPost.id) {
      await updatePost(globalContext.selectedPost);
    } else {
      await addNewPost(globalContext.selectedPost);
    }
    props.postDetailsModified();
  };
  return (
    <div className="w3-card-4 w3-margin w3-white">
      <div className="w3-container">
        <form ref={htmlFormElements}>
        <label htmlFor="title">Title</label><br/>
        <input
          defaultValue={globalContext.selectedPost ? globalContext.selectedPost.title : ""}
          name="title"
        /><br/>
        <label htmlFor="content">Content</label><br/>
        <textarea
          defaultValue={globalContext.selectedPost ? globalContext.selectedPost.content : ""}
          name="content"
        /><br/>
        <button onClick={okClicked}>Ok</button>
        </form>
      </div>
    </div>
  );
}
export default PostDetails;
