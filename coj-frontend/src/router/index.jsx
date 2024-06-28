import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../pages/home/Home";
import ProblemSet from "../pages/problemSet/ProblemSet";
import Problem from "../pages/problem/Problem";

function RouteConfig(props) {
  return (
    <Routes>
      <Route path={"/"} element={<Home />}></Route>
      <Route path={"/home"} element={<Home />}></Route>
      <Route path={"/problemset"} element={<ProblemSet />}></Route>
      <Route path={"/problem"} element={<Problem />}></Route>
    </Routes>
  );
}

export default RouteConfig;
