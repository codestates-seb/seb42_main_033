import styled from 'styled-components';
import PropTypes from 'prop-types';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import api from '../utils/api';

const PostModal = ({
  onEdit,
  onDelete,
  onClose,
  isOpen,
  postId,
  title,
  content,
}) => {
  const handleSubmit = (event) => {
    event.preventDefault();
    onClose();
  };
  PostModal.propTypes = {
    isOpen: PropTypes.bool.isRequired,
    onClose: PropTypes.func.isRequired,
    postId: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
    content: PropTypes.string.isRequired,
  };
  return (
    <>
      {isOpen && (
        <Container>
          <form onSubmit={handleSubmit}>
            <ButtonLink to="/PostPage/${postId}" state={{ title, content }}>
              <Button type="submit" onClick={onEdit}>
                수정하기
              </Button>
            </ButtonLink>
            <Button
              // onClick={async () => {
              //   isOpen(false);
              //   await api.delete(`/api/board/integrated/${postId}`);
              //   alert('게시물이 삭제되었습니다😎');
              //   window.location.href = '/PostListPage';
              // }}
              onClick={onDelete}
            >
              삭제하기
            </Button>
          </form>
        </Container>
      )}
    </>
  );
};

const Container = styled.div`
  position: absolute;
  top: 85px;
  right: 0;
  z-index: 1;
  width: 70px;
  background-color: white;
  margin-right: -75px;
  border: 1px solid #ccc;
`;

const Button = styled.button`
  display: block;
  width: 100%;
  padding: 8px;
  border: none;
  background-color: white;
  height: 30px;
  &:hover {
    background-color: #64b5ff;
  }

  &:first-child {
    border-bottom: 1px solid #ccc;
  }
`;
const ButtonLink = styled(Link)`
  text-decoration: none;
`;

export default PostModal;
