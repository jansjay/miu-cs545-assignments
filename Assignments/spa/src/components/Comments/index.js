import { useContext, useEffect, useState } from "react";
import { getComments } from "../../api/Posts";
import Comment from "../Comment";
import { GlobalContext } from "../../context/GlobalContext";

function Comments(props) {
  const [comments, setComments] = useState([]);
  const globalContext = useContext(GlobalContext);
  
  useEffect(() => {
    getComments(globalContext.selectedPost.id)
      .then((data) => setComments(data))
      .catch((error) => console.log(error));
  }, [globalContext.selectedPost.id]);

  return (
    <div>
      <ul className="w3-ul w3-hoverable w3-white">
        {comments.map((comment) => (
          <Comment key={comment.id} comment={comment} />
        ))}
      </ul>
    </div>
  );
}
export default Comments;
