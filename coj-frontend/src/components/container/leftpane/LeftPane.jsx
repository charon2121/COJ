import React from "react";
import styles from "./LeftPane.module.css";

function LeftPane(props) {
  return <div className={styles.leftPane}>{props.children}</div>;
}

export default LeftPane;
