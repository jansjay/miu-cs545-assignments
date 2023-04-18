function Comment(props) {
  return (
    <div className="w3-card w3-margin">
      <div className="w3-container w3-padding">
        {" "}
        <li className="w3-padding-16">
          <span className="w3-large">{props.comment.id}.</span>
          <br />
          <span>{props.comment.name}</span>
        </li>
      </div>
    </div>
  );
}
export default Comment;
