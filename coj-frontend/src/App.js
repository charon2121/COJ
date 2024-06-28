import React from "react";
import "./App.css";
import Layout from "./components/layout/Layout";
import Header from "./components/layout/header/Header";
import Content from "./components/layout/content/Content";
import RouteConfig from "./router";
import Navbar from "./components/navbar/Navbar";

const App = () => (
  <div id="app">
    <Layout>
      <Header>
        <Navbar></Navbar>
      </Header>
      <Content>
        <RouteConfig />
      </Content>
    </Layout>
  </div>
);
export default App;
