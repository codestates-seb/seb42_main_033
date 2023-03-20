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
      .get(
        `http://ec2-13-125-117-103.ap-northeast-2.compute.amazonaws.com:8080/board/integrated/${userId}`
      )
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
