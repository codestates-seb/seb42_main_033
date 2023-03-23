import BoardList from '../components/BoardList.jsx';
import { useParams } from 'react-router-dom';

const PostlistPage = () => {
  const { id } = useParams();
  return (
    <div>
      <BoardList id={id} />
    </div>
  );
};

export default PostlistPage;
