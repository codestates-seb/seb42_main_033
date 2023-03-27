import BoardCarddetail from '../components/BoardCarddetail.jsx';
import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';

const PostviewPage = () => {
  const { id } = useParams();
  const token = localStorage.getItem('jwtToken');
  const decodedToken = jwtDecode(token);
  const userId = decodedToken.userId;
  const navigate = useNavigate();
  const [isLoaded, setIsLoaded] = useState(false);
  const [comment, setComment] = useState('');
  const [comments, setComments] = useState([]);
  const [post, setPost] = useState({
    title: '',
    username: '',
    content: '',
    likeCount: '',
    commentCount: '',
  });

  //게시글 조회
  useEffect(() => {
    const getPost = async () => {
      try {
        const { data } = await axios.get(
          `${process.env.REACT_APP_API_URL}/board/integrated/${id}`
        );
        // console.log(data);
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
    navigate(`/PostPage/${id}`, {
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
  //댓글 등록
  const handleCommentSubmit = async () => {
    try {
      const config = {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      };
      const data = {
        userId: userId,
        postId: id,
        content: comment,
        username: localStorage.getItem('username'),
      };
      await axios.post(
        `${process.env.REACT_APP_API_URL}/board/integrated/${id}`,
        data,
        config
      );
      setComments([
        ...comments,
        { username: data.username, content: data.content },
      ]);
      setComment('');
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <BoardCarddetail
      id={id}
      post={post}
      isLoaded={isLoaded}
      setIsLoaded={setIsLoaded}
      handleEdit={handleEdit}
      handleDelete={handleDelete}
      handleCommentSubmit={handleCommentSubmit}
      comment={comment}
      setComment={setComment}
      comments={comments}
      setComments={setComments}
    />
  );
};

export default PostviewPage;
