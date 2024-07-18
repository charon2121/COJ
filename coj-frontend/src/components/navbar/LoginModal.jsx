import React from "react";
import { Modal } from "antd";

function LoginModal({ open, onCancel, onOk }) {
  return (
    <Modal open={open} onCancel={onCancel} onOk={onOk}>
      <p>Some contents...</p>
    </Modal>
  );
}

export default LoginModal;
