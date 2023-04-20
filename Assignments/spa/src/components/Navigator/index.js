import { Link } from "react-router-dom";

const Navigator = () => {
  return (
    <div className="w3-row w3-padding w3-black">
      <div className="w3-col s3">
        <Link to="/">Home</Link>  
      </div>
      <div className="w3-col s3">
        <Link to="/posts">Posts</Link>  
      </div>
      <div className="w3-col s3">
        <Link to="/add-post">Add Post</Link>  
      </div>
    </div>
  );
};
export default Navigator;
