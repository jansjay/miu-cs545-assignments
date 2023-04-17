import Post from "../Post";

function Posts(props) {
  return (
    <>
      <div className="w3-col l4">
        <div className="w3-card w3-margin"></div>
      </div>
      <div className="w3-card w3-margin">
        <div className="w3-container w3-padding">
          <h4>Posts</h4>
        </div>
        <ul className="w3-ul w3-hoverable w3-white">
          {props.posts.map((post) => (
            <Post
              key={post.id}
              post={post}
              onSelected={() => props.onSelected(post.id)}
            />
          ))}
        </ul>
      </div>
    </>
  );
}

export default Posts;
