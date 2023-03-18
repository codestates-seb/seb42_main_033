import { Link } from 'react-router-dom';
import styled from 'styled-components';
import QuillEditor from './QuillEditor.js';

const BoardCreateOrEdit = (props) => {
  return (
    <BoardForm onSubmit>
      <Title className="titlediv">
        <p>글쓰기</p>
        <input
          placeholder=" 글 제목"
          type="text"
          name="title"
          value={props.TitleValue}
          onChange={props.handleTitleChange}
          // defaultValue={props.TitleValue}
        />
        <hr />
      </Title>
      <QuillEditor
        name="contents"
        value={props.ContentValue}
        onChange={props.handleContentChange}
        // defaultValue={props.ContentValue}
      />
      <ButtonContainer>
        <BoardButtonLink to="/PostlistPage">
          <BoardButton className="delete"> 취소 </BoardButton>
        </BoardButtonLink>
        <BoardButtonLink to="/PostviewPage">
          <BoardButton
            type="submit"
            className="submit"
            onClick={props.handleSubmit}
          >
            {/* {props.updateRequest ? '수정' : '등록'} */}
            등록
          </BoardButton>
        </BoardButtonLink>
      </ButtonContainer>
    </BoardForm>
  );
};
const BoardForm = styled.form`
  @media only screen and (min-width: 1441px) {
    //해상도 1440보다 큰 모니터
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: 90vh;
    font-size: 40px;
    gap: 10px;
  }
  @media only screen and (max-width: 1440px) {
    height: 800px;
    font-size: 20px;
  }
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-left: 600px;
  margin-top: 20px;
  width: 220px;
`;
const BoardButtonLink = styled(Link)`
  text-decoration: none;
`;
const BoardButton = styled.button`
  width: 106px;
  height: 54px;
  text-align: center;
  font-size: 20px;
  font-weight: 700;
  color: #ffffff;
  border-radius: 10px;
  border: 0;
  letter-spacing: 7px;
  text-decoration: none;
  &.delete {
    background-color: #a1a1a1;
  }
  &.submit {
    background-color: #64b5ff;
  }
`;

const Title = styled.div`
  p {
    font-size: 40px;
    margin-bottom: 10px;
    font-weight: 600;
  }
  input {
    width: 825px;
    height: 40px;
    border-radius: 10px;
    border-width: thin;
    font-size: 18px;
    padding-left: 10px;
  }
`;
export default BoardCreateOrEdit;
