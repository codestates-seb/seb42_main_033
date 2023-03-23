import styled from 'styled-components';

const BoardCard = () => {
  return (
    <div>
      <CardLayout>
        <div className="posttitle"> 제목 </div>
        <div className="postcontent"> 나의 질문은 이것이다 휴먼 </div>
        <div className="postnickname"> 알파고 </div>
      </CardLayout>
    </div>
  );
};
const CardLayout = styled.div`
  display: flex;
  flex-direction: column;
  width: 908px;
  height: 111px;
  background-color: #ffffff;
  /* justify-content: center;
    align-items: center; */
  margin-left: 10px;
  margin-top: 14px;
  text-decoration: none;
  div.posttitle {
    font-size: 1.8rem;
    text-align: left;
    padding-top: 10px;
    padding-left: 10px;
  }
  div.postcontent {
    text-align: left;
    padding-top: 10px;
    padding-left: 10px;
  }
  div.postnickname {
    text-align: right;
    padding-right: 10px;
  }
`;
export default BoardCard;
