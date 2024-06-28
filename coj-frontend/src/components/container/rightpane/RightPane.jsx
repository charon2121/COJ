import React from "react";
import styles from "./RightPane.module.css";

function RightPane(props) {
  return <div className={styles.rightPane}>{props.children}</div>;
}

export default RightPane;
