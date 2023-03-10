import styled from 'styled-components';
import PropTypes from 'prop-types';

const PostModal = ({ onClose, isOpen }) => {
  const handleSubmit = (event) => {
    event.preventDefault();
    onClose();
  };

  return (
    <>
      {isOpen && (
        <Container>
          <form onSubmit={handleSubmit}>
            <Button type="submit">수정하기</Button>
            <Button onClick={onClose}>삭제하기</Button>
          </form>
        </Container>
      )}
    </>
  );
};
PostModal.propTypes = {
  isOpen: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
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

export default PostModal;
