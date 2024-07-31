/** @jsxImportSource @emotion/react */
import React, { useState } from "react";
import { Avatar, Drawer, Flex, Menu, Popover } from "antd";
import styles from "./Navbar.module.css";
import { BellOutlined } from "@ant-design/icons";
import { useSelector } from "react-redux";
import { loginFont } from "./style";
import LoginModal from "./LoginModal";
import { menuItems } from "./menuItems";
import {userLogin} from "../../api/user";

function Navbar() {
  const isLogin = useSelector((state) => state.user.isLogin);

  const [current, setCurrent] = useState("home");

  const clickHandle = (e) => {
    setCurrent(e.key);
  };

  /**
   * 管理抽屉状态
   */
  const [open, setOpen] = useState(false);

  const showDrawer = () => {
    setOpen(true);
  };

  const onClose = () => {
    setOpen(false);
  };

  /**
   * 管理登录的模态框的状态
   */
  const [loginModalOpen, setLoginModalOpen] = useState(false);

  return (
    <nav className={styles.navbar}>
      <div>
        <Menu
          onClick={clickHandle}
          selectedKeys={[current]}
          mode={"horizontal"}
          items={menuItems}
          style={{ lineHeight: "var(--navbar-height)" }}
        ></Menu>
      </div>
      {isLogin && (
        <Flex gap={24}>
          <BellOutlined onClick={showDrawer} />
          <Popover
            placement="bottomRight"
            content={<div>hello world</div>}
            arrow={false}
            trigger={"click"}
            overlayStyle={{ top: "56px" }}
          >
            <Avatar>U</Avatar>
          </Popover>
          <Drawer
            title="消息中心"
            onClose={onClose}
            open={open}
            mask={false}
            maskClosable={true}
            width={500}
          >
            <p>Some contents...</p>
            <p>Some contents...</p>
            <p>Some contents...</p>
          </Drawer>
        </Flex>
      )}
      {!isLogin && (
        <Flex>
          <div css={loginFont} onClick={() => setLoginModalOpen(true)}>
            登录<span> / </span>注册
          </div>
          <LoginModal
            open={loginModalOpen}
            onCancel={() => setLoginModalOpen(false)}
            onOk={async (value) => {
              const { username, password } = value;
              await userLogin(username, password)
            }}
          />
        </Flex>
      )}
    </nav>
  );
}

export default Navbar;
