import { Link } from 'react-router-dom';
import styled from 'styled-components';
import QuillEditor from './QuillEditor.jsx';

const BoardCreateOrEdit = ({
  setTitle,
  setContent,
  title,
  content,
  handleSubmit,
}) => {
  return (
    <BoardForm onSubmit={handleSubmit}>
      <input
        placeholder=" 글 제목"
        type="text"
        name="title"
        value={title}
        onChange={(e) => {
          setTitle(e.target.value);
        }}
      />
      <br />
      <hr />
      <QuillEditor
        name="content"
        value={content}
        onChange={(e) => {
          setContent(e.target.value);
        }}
      />
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
  input {
    width: 825px;
    height: 40px;
    margin-top: -100px;
    margin-bottom: -70px;
    border-radius: 10px;
    border-width: thin;
    font-size: 18px;
    padding-left: 10px;
  }
  hr {
    width: 100%;
    border: none;
    border-top: 1px solid black;
    margin-top: 20px;
    margin-bottom: 15px;
  }
`;

export default BoardCreateOrEdit;
