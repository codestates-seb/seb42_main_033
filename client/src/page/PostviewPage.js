import BoardCarddetail from '../components/BoardCarddetail.jsx';
import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

const PostviewPage = () => {
  const { id } = useParams();
  const token = localStorage.getItem('jwtToken');
  const navigate = useNavigate();
  const [isLoaded, setIsLoaded] = useState(false);
  const [post, setPost] = useState({});
  //게시글 조회
  useEffect(() => {
    const getPost = async () => {
      try {
        const { data } = await axios.get(
          `${process.env.REACT_APP_API_URL}/board/integrated/${id}`
        );
        console.log(data);
        setPost(data);
        setIsLoaded(true);
      } catch (error) {
        console.log(error);
      }
    };
    getPost();
  }, [id]);
  // 게시글 수정
  const handleEdit = async () => {
    navigate(`/PostEditPage/${id}`, {
      state: { title: post.title, content: post.content },
    });
  };
  // 게시글 삭제
  const handleDelete = async () => {
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    try {
      await axios.delete(
        `${process.env.REACT_APP_API_URL}/board/integrated/${id}`,
        config
      );
      navigate('/PostlistPage');
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <BoardCarddetail
      id={id}
      post={post}
      setPost={setPost}
      isLoaded={isLoaded}
      setIsLoaded={setIsLoaded}
      handleEdit={handleEdit}
      handleDelete={handleDelete}
    />
  );
};

export default PostviewPage;
