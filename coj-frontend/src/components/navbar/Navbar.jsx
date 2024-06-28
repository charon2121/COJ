import React, { useState } from "react";
import { Menu } from "antd";
import { NavLink } from "react-router-dom";
import styles from "./Navbar.module.css";

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
      <div>
      </div>
    </nav>
  );
}

export default Navbar;
