import React, { useState } from "react";
import { Button, Form, Input, Modal, Segmented } from "antd";

function LoginModal({ open, onCancel, onOk }) {
  const [value, setValue] = useState("登录");
  const [model, setModel] = useState("login");

  return (
    <Modal open={open} onCancel={onCancel} onOk={onOk} footer={null}>
      <div style={{ paddingTop: 32, paddingLeft: 24, paddingRight: 24 }}>
        <Segmented
          options={["登录", "注册"]}
          value={value}
          onChange={(value) => {
            setValue(value);
            setModel(value === "登录" ? "login" : "register");
          }}
          block
        />
      </div>
      <div style={{ paddingTop: 32, paddingLeft: 24, paddingRight: 24 }}>
        <Form wrapperCol={{ span: 24 }} layout={"vertical"}>
          <Form.Item label={"用户名"} name={"username"}>
            <Input />
          </Form.Item>
          <Form.Item label={"密码"} name={"password"}>
            <Input />
          </Form.Item>
          <Form.Item>
            <Button block type={"primary"}>
              登录
            </Button>
          </Form.Item>
        </Form>
      </div>
    </Modal>
  );
}

export default LoginModal;
