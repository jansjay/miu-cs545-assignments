import { useContext, useEffect, useState } from "react";
import { getComments } from "../../api/Posts";
import Comment from "../Comment";

function Comments(props) {
  const [comments, setComments] = useState([]);
  
  useEffect(() => {
    getComments(props.id)
      .then((data) => setComments(data))
      .catch((error) => console.log(error));
  }, [props.id]);

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
