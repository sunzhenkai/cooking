import React, { Component } from 'react';
import { Upload, Button, Icon } from 'antd';

// const props = {
//   name: 'file',
//   action: 'http://localhost:8080/upload/image',
//   headers: {
//     authorization: 'authorization-text',
//   },
//   onChange(info) {
//     if (info.file.status === 'done') {
//       console.log("upload image done");
//     }
//     // console.log(info.file.status);

//     // if (info.file.status !== 'uploading') {
//     //   console.log(info.file, info.fileList);
//     // }
//     // if (info.file.status === 'done') {
//     //   console.log(`${info.file.name} file uploaded successfully`);
//     // } else if (info.file.status === 'error') {
//     //   console.log(`${info.file.name} file upload failed.`);
//     // } else {
//     //   console.log(info);
//     //   console.log("HELLO");
//     // }
//   },
//   // onSuccess(info) {
//   //   console.log(info);
//   // }
// };

export default class Uploader extends Component {

  constructor(props) {
    super(props);
    this.state = {
      imageList: []
    };
  }

  handleChange = e => {
    if (e.file.status === 'done') {
      if (e.file.response.ret) {
        console.log('图片上传成功');
      } else {
        console.log('请求成功，但是服务端业务逻辑处理出错');
      }
    } else if (e.file.status === 'error') {
      console.log('请求出错(40x，页面存在、请求参数不对等)，服务端抛出没有处理异常(50X)')
    }
    this.setState({ imageList: [...(e.fileList)] });
  }

  render() {
    return (<Upload
      name='file'
      fileList={this.state.imageList}
      action='http://localhost:8080/upload/image'
      onChange={this.handleChange}
      >

      <Button>
        <Icon type="upload" /> Click to Upload
      </Button>
    </Upload>
    )
  }
}