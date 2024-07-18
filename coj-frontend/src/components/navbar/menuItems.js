import { NavLink } from "react-router-dom";
import React from "react";

export const menuItems = [
  {
    label: <NavLink to={"/home/"}>首页</NavLink>,
    key: "home",
  },
  {
    label: <NavLink to={"/problemset/"}>题库</NavLink>,
    key: "problemset",
  },
];
