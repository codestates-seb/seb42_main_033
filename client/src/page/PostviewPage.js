import BoardCarddetail from '../components/BoardCarddetail';
// import { useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import axios from 'axios';
import { postActions } from '../slice/postSlice';

const PostviewPage = ({ match }) => {
  // const { userId } = useParams();
  // const [post, setPost] = useState([]);
  // console.log(post);
  console.log(match.params.postId);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(postActions.getPost(match.params.postId));
  }, [match.params.postId]);

  const { title, username, content } = useSelector((state) => ({
    title: state.postReducers.title,
    username: state.postReducers.username,
    content: state.postReducers.content,
  }));
  const createdAt = useSelector((state) => state.postReducers.createdAt);
  const likeCount = useSelector((state) => state.postReducers.likeCount);
  const commentCount = useSelector((state) => state.postReducers.commentCount);

  useEffect(() => {
    axios
      .get
      // `ec2-3-38-150-208.ap-northeast-2.compute.amazonaws.com/board/integrated/${userId}`
      ()
      .then((response) => {
        console.log(response);
        // setPost(response.data);
      });
  }, []);

  // if (!post) {
  //   return <div>Loading...</div>;
  // }
  return (
    <BoardCarddetail
      title={title}
      username={username}
      content={content}
      createdAt={createdAt}
      likeCount={likeCount}
      commentCount={commentCount}
    />
  );
};

export default PostviewPage;
