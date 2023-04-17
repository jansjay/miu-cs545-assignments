function PostDetails(props) {
  if (props.post && props.post.id) {
    return (
      <div className="w3-card-4 w3-margin w3-white">
        <div className="w3-container">
          <h3>{props.post.title}</h3>
          <h5>{props.post.author}</h5>
          <div className="w3-container">
            <p>{props.post.details}</p>
          </div>
          <div className="w3-row">
            <div className="w3-col m8 s12">
              <p>
                <button onClick={props.onEdit}>edit</button>
                <button onClick={props.onDelete}>delete</button>
              </p>
            </div>
          </div>
        </div>
      </div>
    );
  }
  return "";
}
export default PostDetails;
