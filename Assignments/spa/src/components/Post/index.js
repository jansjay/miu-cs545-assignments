function Post(props) {
  return (
    <div onClick={props.onSelected}>
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
