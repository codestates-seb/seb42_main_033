import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { useMemo } from 'react';

const QuillEditor = () => {
  // const [inputvalue, setInputvalue] = useState('');

  const modules = useMemo(
    () => ({
      toolbar: {
        container: [
          ['bold', 'italic', 'underline', 'strike', 'blockquote'],
          [{ size: ['small', false, 'large', 'huge'] }, { color: [] }],
          [
            { list: 'ordered' },
            { list: 'bullet' },
            { indent: '-1' },
            { indent: '+1' },
            { align: [] },
          ],
          ['image', 'video'],
        ],
      },
    }),
    []
  );
  return (
    <div>
      <ReactQuill
        modules={modules}
        theme="snow"
        placeholder="내용을 입력해주세요."
        style={{
          height: '430px',
          marginBottom: '60px',
          width: '840px',
        }}
      />
    </div>
  );
};

export default QuillEditor;
