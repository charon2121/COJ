import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../pages/home/Home";
import ProblemSet from "../pages/problemSet/ProblemSet";
import Problem from "../pages/problem/Problem";

function RouteConfig() {
  return (
    <Routes>
      <Route path={"/"} element={<Home />} />
      <Route path={"/home"} element={<Home />} />
      <Route path={"/problemset"} element={<ProblemSet />} />
      <Route path={"/problem"} element={<Problem />} />
    </Routes>
  );
}

export default RouteConfig;
