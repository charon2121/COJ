import React from "react";
import { Flex, Modal, Segmented } from "antd";

function LoginModal({ open, onCancel, onOk }) {
  return (
    <Modal open={open} onCancel={onCancel} onOk={onOk} footer={null}>
      <div style={{ paddingTop: 32 }}>
        <Segmented options={["登录", "注册"]} block></Segmented>
      </div>
    </Modal>
  );
}

export default LoginModal;
