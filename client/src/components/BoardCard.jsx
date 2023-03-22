import styled from 'styled-components';

const BoardCard = (post) => {
  return (
    <div>
      <CardLayout>
        <div className="posttitle"> {post.title} </div>
        <div className="postcontent"> {post.contents} </div>
        <div className="postnickname">{post.nickname}</div>
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
