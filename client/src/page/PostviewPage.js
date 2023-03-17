import BoardCarddetail from '../components/BoardCarddetail';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

const PostviewPage = () => {
  const { userId } = useParams();
  const [post, setPost] = useState([]);
  // console.log(post);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/board/integrated/${userId}`)
      .then((response) => {
        console.log(response);
        setPost(response.data);
      });
  }, []);

  if (!post) {
    return <div>Loading...</div>;
  }
  return <BoardCarddetail post={post} />;
};

export default PostviewPage;
