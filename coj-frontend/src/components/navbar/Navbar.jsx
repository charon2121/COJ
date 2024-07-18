/** @jsxImportSource @emotion/react */
import React, { useState } from "react";
import { Avatar, Drawer, Flex, Menu, Popover } from "antd";
import { NavLink } from "react-router-dom";
import styles from "./Navbar.module.css";
import { BellOutlined } from "@ant-design/icons";

function Navbar(props) {
  const [current, setCurrent] = useState("home");

  const items = [
    {
      label: <NavLink to={"/home/"}>首页</NavLink>,
      key: "home",
    },
    {
      label: <NavLink to={"/problemset/"}>题库</NavLink>,
      key: "problemset",
    },
  ];

  const clickHandle = (e) => {
    setCurrent(e.key);
  };

  const [open, setOpen] = useState(false);
  const showDrawer = () => {
    setOpen(true);
  };
  const onClose = () => {
    setOpen(false);
  };

  return (
    <nav className={styles.navbar}>
      <div>
        <Menu
          onClick={clickHandle}
          selectedKeys={[current]}
          mode={"horizontal"}
          items={items}
          style={{ lineHeight: "var(--navbar-height)" }}
        ></Menu>
      </div>
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
    </nav>
  );
}

export default Navbar;
