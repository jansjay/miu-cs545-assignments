import { useNavigate } from "react-router-dom";

function Post(props) {  
  const navigate = useNavigate();
  const onSelected = (id) => {
    navigate(`/posts/${id}`);
  };
  return (
    <div onClick={() => {onSelected(props.post.id) }}>
      <li className="w3-padding-16">
        <span className="w3-large">
          {props.post.id}. {props.post.title}
        </span>
        <br />
        <span>{props.post.user.name}</span>
      </li>
    </div>
  );
}

export default Post;
