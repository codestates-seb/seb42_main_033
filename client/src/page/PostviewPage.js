import BoardCarddetail from '../components/BoardCarddetail.jsx';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

const PostviewPage = () => {
  const { id } = useParams();
  const [isLoaded, setIsLoaded] = useState(false);
  const [post, setPost] = useState({});

  useEffect(() => {
    const getPost = async () => {
      try {
        const { data } = await axios.get(
          `${process.env.REACT_APP_API_URL}/board/integrated/${id}`
        );
        setPost(data);
        setIsLoaded(true);
      } catch (error) {
        console.log(error);
      }
    };
    getPost();
  }, [id]);

  return (
    <BoardCarddetail
      id={id}
      post={post}
      isLoaded={isLoaded}
      setIsLoaded={setIsLoaded}
    />
  );
};

export default PostviewPage;
